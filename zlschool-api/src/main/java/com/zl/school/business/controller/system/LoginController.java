package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Validated
@RequestMapping("/system")
@Api(value = "login", description = "登陆接口", tags = {"LoginController"})
public class LoginController extends ExceptionController {
    @Autowired
    private ILoginService loginServece;

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParam(name = "loginReq", value = "用户注册", required = true, dataType = "LoginReq")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginRes login(@RequestBody LoginReq loginReq, HttpServletRequest request) {
        return loginServece.login(loginReq,request);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiImplicitParam(name = "registerReq", value = "用户注册", required = true, dataType = "RegisterReq")
    public void register(@RequestBody RegisterReq registerReq, HttpServletRequest request) {
            loginServece.register(registerReq);//注册用户信息
    }

    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    @ApiImplicitParam(name = "forgetPasswordReq", value = "忘记密码", required = true, dataType = "ForgetPasswordReq")
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public void forgetPassword(@RequestBody ForgetPasswordReq forgetPasswordReq, HttpServletRequest request) {
        loginServece.ForgetPassword(forgetPasswordReq);
    }

    @ApiOperation(value = "查询用户登陆session", notes = "查询用户登陆session")
    @RequestMapping(value = "/getSession", method = RequestMethod.POST)
    public GetSessionRes getSession(HttpServletRequest request) {
        return loginServece.getSession(request);
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpServletRequest request) {
        loginServece.loginOut(request);
    }

    @ApiOperation(value = "进入系统时，根据用户查询用户菜单权限", notes = "进入系统时，根据用户查询用户菜单权限")
    @RequestMapping(value = "/getUserPower", method = RequestMethod.POST)
    public GetUserPowerRes getUserPower(HttpServletRequest request) {
        return loginServece.getUserPower(request);
    }

    @ApiOperation(value = "根据菜单查询用户按钮权限", notes = "根据菜单查询用户按钮权限")
    @RequestMapping(value = "/getUserOperator/{menuId}", method = RequestMethod.POST)
    public List<GetUserOperatorRes> getUserOperator(@PathVariable("menuId") String menuId,HttpServletRequest request) {
        return loginServece.getUserOperator(menuId,request);
    }
}
