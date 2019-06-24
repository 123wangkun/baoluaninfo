package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.User;

import java.util.List;

/**
 * @author MrBird
 */
public interface UserMapper extends BaseDao<User, String> {

    /**
     * 查询用户列表
     * @return
     */
    List<GetUserListRes> getUserList(GetUserListReq getUserListReq);

    /**
     * 查询个人用户列表
     * @return
     */
    List<GetPersonalUserListRes> getPersonalUserList(GetPersonalUserListReq getUserListReq);

    /**
     * 查询企业用户详情
     * @return
     */
    GetUserRes getUser(String userId,String eId );

    /**
     * 根据手机号查询是否存在该用户
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 判断登录名是否已存在
     * @return
     */
    int isExistLoginName(String loginName);

    /**
     * 查询个人用户详情
     * @return
     */
    GetPersonalRes getPersonal(String userId, String eId );
}
