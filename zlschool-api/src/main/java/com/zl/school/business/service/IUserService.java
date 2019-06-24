package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */
public interface IUserService extends IBaseService<User, String> {

    /**
     * 查询企业用户列表
     * @return
     */
    RspPage<GetUserListRes> getUserList(GetUserListReq getUserListReq, HttpServletRequest request);

    /**
     * 查询个人用户列表
     * @return
     */
    RspPage<GetPersonalUserListRes> getPersonalUserList(GetPersonalUserListReq getPersonalUserListReq, HttpServletRequest request);

    /**
     * 查询用户详情
     * @return
     */
    GetUserRes getUser(String userId, HttpServletRequest request);

    /**
     * 新增用户
     * @return
     */
    void addUser(AddUserReq addUserReq,HttpServletRequest request);

    /**
     * 修改用户
     * @return
     */
    void editUser(EditUserReq editUserReq);

    /**
     * 删除用户
     * @return
     */
    void deleteUser(DeleteUserReq deleteUserReq, HttpServletRequest request);

    /**
     * 找回密码
     * @return
     */
    void EditPassword(EditPasswordReq editPasswordReq);

    /**
     * 进入修改个人用户界面
     * @return
     */
    GetPersonalRes getPersonal(String userId, HttpServletRequest request);

    /**
     * 修改个人用户信息
     * @return
     */
    void editPersonal(GetPersonalReq personalReq);
}
