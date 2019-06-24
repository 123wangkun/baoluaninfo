package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.service.IRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/system")
@Api(value = "知识管理后台API", description = "角色管理", tags = {"RoleController"})
public class RoleController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IRoleService service;

    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public List<GetRoleListRes> getRoleList(HttpServletRequest request) {
        return service.getRoleList(request);
    }

    @ApiOperation(value = "进入添加角色界面时，查询菜单目录树", notes = "进入添加角色界面时，查询菜单目录树")
    @RequestMapping(value = "/getAllMenuTree", method = RequestMethod.POST)
    public GetAllMenuTreeRes getAllMenuTree(HttpServletRequest request) {
        return service.getAllMenuTree(request);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParam(name = "addRoleReq", value = "新增用户", required = true, dataType = "AddRoleReq")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public void addRole(@RequestBody AddRoleReq addRoleReq, HttpServletRequest request) {
        service.addRole(addRoleReq,request);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @ApiImplicitParam(name = "editRoleReq", value = "修改角色", required = true, dataType = "EditRoleReq")
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public void editRole(@RequestBody EditRoleReq editRoleReq, HttpServletRequest request) {
        service.editRole(editRoleReq,request);
    }

    @ApiOperation(value = "查询角色详情", notes = "查询角色详情")
    @RequestMapping(value = "/rolePower/{roleId}", method = RequestMethod.GET)
    public GetMenuTreeRes rolePower(@PathVariable("roleId") String roleId, HttpServletRequest request) {
        return service.rolePower(roleId,request);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/deleteRole/{roleId}", method = RequestMethod.GET)
    public void deleteRole(@PathVariable("roleId") String roleId, HttpServletRequest request) {
        service.deleteRole(roleId,request);
    }
}
