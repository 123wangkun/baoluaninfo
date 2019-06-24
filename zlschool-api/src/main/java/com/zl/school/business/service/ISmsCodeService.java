package com.zl.school.business.service;

import com.zl.school.business.dto.system.CheckSmsReq;
import com.zl.school.business.dto.system.SendSmsRes;
import com.zl.school.business.entity.system.SmsCode;

/**
 * @author MrBird
 */
public interface ISmsCodeService extends IBaseService<SmsCode,SmsCode> {

    /**
     * 校验短信验证码
     * @return
     */
    Boolean checkSmsCode(CheckSmsReq checkSmsReq);

    /**
     * 更新短信验证码数据表的记录
     * @return
     */
    SendSmsRes sendSmsCode(String phone, int type);
}
