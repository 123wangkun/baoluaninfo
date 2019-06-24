package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.dao.system.SmsCodeMapper;
import com.zl.school.business.dto.system.CheckSmsReq;
import com.zl.school.business.dto.system.SendSmsRes;
import com.zl.school.business.entity.system.SmsCode;
import com.zl.school.business.service.ISmsCodeService;
import com.zl.school.business.util.sendsms.SendSmsUtil;
import com.zl.school.business.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author MrBird
 */
@Service
public class SmsCodeServiceImpl extends AbstractServiceImpl<SmsCode,SmsCode> implements ISmsCodeService {

    @Autowired
    private SmsCodeMapper smsCodeMapper;

    public SmsCodeServiceImpl(SmsCodeMapper smsCodeMapper) {
        super(smsCodeMapper);
        this.smsCodeMapper = smsCodeMapper;
    }

    /**
     * 发送短信验证码
     * @return
     */
    @Override
    public SendSmsRes sendSmsCode(String phone, int type){
        if(StringUtil.isEmpty(phone) || phone.length() != 11) {
            throw new BusinessException(ErrorCode.ERROR_PHONE,"无效手机号");
        }

        // 1:随机生成6位数的验证码
        String smsCode = "";
        for (int i = 0; i < 6; i++) {
            smsCode = smsCode + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }

        // 3: 判断短信验证码类型(1:注册;2:修改密码)
        int expiredtime = 0;
        if(type == 1) {
            //用户注册
            expiredtime = 5 * 60;
        } else if(type == 2) {
            //修改密码
            expiredtime = 1 * 60;
        }else {
            throw new BusinessException(ErrorCode.ERROR_PHONE, "微信短信类型不支持！");
        }

        //获取服务器IP地址
        String clientIp = "222.94.140.246";
        //发送短信验证码
        Boolean flag = SendSmsUtil.sendSMS(phone, smsCode, expiredtime, clientIp);
        if(flag){
            // 保存验证码时，查看表中是否已存在该手机号信息
            int isExistSmscode = smsCodeMapper.isExistPhone(phone, type);
            SmsCode smsCodeEntity = new SmsCode();
            smsCodeEntity.setMobile(phone);
            smsCodeEntity.setType(type);
            smsCodeEntity.setSmscode(smsCode);
            smsCodeEntity.setRequestdt(new Date());
            smsCodeEntity.setExpiredtime(expiredtime);
            if (isExistSmscode == 1) {
                // 表中已存在该手机号信息,更新数据
                smsCodeMapper.updateByPrimaryKeySelective(smsCodeEntity);
            } else {
                // 表中不存在该手机号信息,新增数据
                smsCodeMapper.insertSelective(smsCodeEntity);
            }
        }else{
            //打印错误日志
            throw new BusinessException(ErrorCode.ERROR_PHONE, "发送手机验证码失败！");
        }

        SendSmsRes sendSmsRes = new SendSmsRes();
        sendSmsRes.setSmscode(smsCode);
        sendSmsRes.setExpiredtime(expiredtime);
        return sendSmsRes;
    }

    /**
     * 校验短信验证码
     * @return
     */
    @Override
    public Boolean checkSmsCode(CheckSmsReq checkSmsReq){
        //1根据手机号、类型、验证码去数据库查询该记录是否存在
        SmsCode sms = smsCodeMapper.selectBySmsCode(checkSmsReq);//根据手机号以及type查询该条记录

        //2不存在，直接报验证码不正确
        if(sms != null) {
            //3存在此时判断验证码是否超时
            Date presentTime = new Date();//获取系统当前时间
            Date requestTime = sms.getRequestdt();//获取数据库发送验证码的请求时间
            long time = presentTime.getTime() - requestTime.getTime();//两个时间相减
            int timeInt = new Long(time/ (1000)).intValue();//类型转换
            if (timeInt > sms.getExpiredtime()) {//如果时间已经超过了设置的有效期
                throw new BusinessException(ErrorCode.ERROR_PHONE, "验证码已过期！");
            }else{
                return true;
            }
        }else{
            throw new BusinessException(ErrorCode.ERROR_PHONE, "验证码不正确！");
        }
    }
}
