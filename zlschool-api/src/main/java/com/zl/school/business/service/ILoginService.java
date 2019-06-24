package com.zl.school.business.service;

import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ILoginService extends IBaseService<User, String> {
    /**
     * 用户登陆
     * @return
     */
    LoginRes login(LoginReq loginReq,HttpServletRequest request);

    /**
     * 用户注册
     * @return
     */
    void register(RegisterReq registerReq);

    /**
     * 忘记密码
     * @return
     */
    void ForgetPassword(ForgetPasswordReq forgetPasswordReq);

    /**
     * 查询用户登陆session
     * @return
     */
    GetSessionRes getSession(HttpServletRequest request);

    /**
     * 退出登陆
     * @return
     */
    void loginOut(HttpServletRequest request);

    /**
     * 进入系统时，根据用户查询用户菜单权限
     * @return
     */
    GetUserPowerRes getUserPower(HttpServletRequest request);

    /**
     * 根据菜单id查询用户操作权限，即按钮
     * @return
     */
    List<GetUserOperatorRes> getUserOperator(String menuId, HttpServletRequest request);
}
