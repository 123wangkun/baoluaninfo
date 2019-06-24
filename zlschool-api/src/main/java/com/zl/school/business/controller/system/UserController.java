package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@RestController
@Validated
@RequestMapping("/system")
@Api(value = "user", description = "用户管理", tags = {"UserController"})
public class UserController extends ExceptionController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "组织管理 --查询该企业下的所有用户列表", notes = "组织管理 --查询该企业下的所有用户列表")
    @ApiImplicitParam(name = "getUserListReq", value = "组织管理 --查询该企业下的所有用户列表", required = true, dataType = "GetUserListReq")
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public RspPage<GetUserListRes> getUserList(@RequestBody GetUserListReq getUserListReq, HttpServletRequest request) {
        return userService.getUserList(getUserListReq,request);
    }

    @ApiOperation(value = "组织管理 -- 查询用户详情", notes = "组织管理 -- 查询用户详情")
    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.POST)
    public GetUserRes getUser(@PathVariable("userId") String userId, HttpServletRequest request) {
        return userService.getUser(userId,request);
    }

    @ApiOperation(value = "组织管理 -- 新增用户", notes = "组织管理 --新增用户")
    @ApiImplicitParam(name = "addUserReq", value = "组织管理 --新增用户", required = true, dataType = "AddUserReq")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserReq addUserReq, HttpServletRequest request) {
         userService.addUser(addUserReq,request);
    }

    @ApiOperation(value = "组织管理 --修改用户", notes = "组织管理 --修改用户")
    @ApiImplicitParam(name = "editUserReq", value = "组织管理 --修改用户", required = true, dataType = "EditUserReq")
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public void editUser(@RequestBody EditUserReq editUserReq, HttpServletRequest request) {
        userService.editUser(editUserReq);
    }

    @ApiOperation(value = "组织管理 --删除用户", notes = "组织管理 --删除用户")
    @ApiImplicitParam(name = "deleteUserReq", value = "组织管理 --删除用户", required = true, dataType = "DeleteUserReq")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody DeleteUserReq deleteUserReq, HttpServletRequest request) {
        userService.deleteUser(deleteUserReq,request);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParam(name = "editPasswordReq", value = "修改密码", required = true, dataType = "EditPasswordReq")
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public void EditPassword(@RequestBody EditPasswordReq editPasswordReq, HttpServletRequest request) {
        userService.EditPassword(editPasswordReq);
    }


    @ApiOperation(value = "个人用户管理--查询个人用户列表", notes = "个人管理--查询个人用户列表")
    @ApiImplicitParam(name = "getPersonalUserListReq", value = "个人管理--查询个人用户列表", required = true, dataType = "GetPersonalUserListReq")
    @RequestMapping(value = "/getPersonalUserList", method = RequestMethod.POST)
    public RspPage<GetPersonalUserListRes> getPersonalUserList(@RequestBody GetPersonalUserListReq getPersonalUserListReq, HttpServletRequest request) {
        return userService.getPersonalUserList(getPersonalUserListReq,request);
    }

    @ApiOperation(value = "个人用户管理 -- 进入修改用户界面", notes = "个人用户管理 -- 进入修改用户界面")
    @RequestMapping(value = "/getPersonal/{userId}", method = RequestMethod.GET)
    public GetPersonalRes getPersonal(@PathVariable("userId") String userId, HttpServletRequest request) {
        return userService.getPersonal(userId,request);
    }

    @ApiOperation(value = "个人用户管理 -- 修改个人用户信息", notes = "个人用户管理 -- 修改个人用户信息")
    @ApiImplicitParam(name = "personalReq", value = "个人用户管理 -- 修改个人用户信息", required = true, dataType = "GetPersonalReq")
    @RequestMapping(value = "/editPersonal", method = RequestMethod.POST)
    public void editPersonal(@RequestBody GetPersonalReq personalReq, HttpServletRequest request) {
        userService.editPersonal(personalReq);
    }
}
