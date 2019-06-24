package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.system.LoginReq;
import com.zl.school.business.entity.system.User;

public interface LoginMapper extends BaseDao<User, String> {
    /**
     * 获取用户信息—手机号码
     *
     * @return
     */
    User getUserByPhone(LoginReq loginReq);
    /**
     * 判断手机号是否已存在
     * @return
     */
    Integer isExistPhone(String phone);
}
