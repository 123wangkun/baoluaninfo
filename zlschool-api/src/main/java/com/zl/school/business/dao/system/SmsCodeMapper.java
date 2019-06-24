package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.system.CheckSmsReq;
import com.zl.school.business.entity.system.SmsCode;

/**
 * @author MrBird
 */
public interface SmsCodeMapper extends BaseDao<SmsCode,SmsCode> {

    /**
     * 判断手机号是否已存在
     * @return
     */
    int isExistPhone(String phone, int type);

    /**
     * 校验验证码
     * @return
     */
    SmsCode selectBySmsCode(CheckSmsReq checkSmsReq);

}
