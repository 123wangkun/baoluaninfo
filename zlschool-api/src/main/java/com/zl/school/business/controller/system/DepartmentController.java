package com.zl.school.business.controller.system;

import com.zl.school.business.dto.system.AddDepartmentReq;
import com.zl.school.business.dto.system.EditDepartmentReq;
import com.zl.school.business.dto.system.GetDepartmentRes;
import com.zl.school.business.dto.system.GetDepartmentTreeRes;
import com.zl.school.business.service.IDepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/system")
@Api(value = "知识管理后台API", description = "部门管理", tags = {"DepartmentController"})
public class DepartmentController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IDepartmentService service;

    @ApiOperation(value = "组织管理--查询企业目录树", notes = "组织管理--查询企业目录树")
    @RequestMapping(value = "/getDepartmentTree", method = RequestMethod.POST)
    public GetDepartmentTreeRes getDepartmentTree(HttpServletRequest request) {
        return service.getDepartmentTree(request);
    }

    @ApiOperation(value = "组织管理--查询部门详情", notes = "组织管理--查询部门详情")
    @RequestMapping(value = "/getDepartment/{depId}", method = RequestMethod.GET)
    public GetDepartmentRes getDepartment(@PathVariable("depId") String depId, HttpServletRequest request) {
        return service.getDepartment(depId,request);
    }

    @ApiOperation(value = "组织管理--新增部门", notes = "组织管理--新增部门")
    @ApiImplicitParam(name = "addDepartmentReq", value = "组织管理--新增部门", required = true, dataType = "AddDepartmentReq")
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public void addDepartment(@RequestBody AddDepartmentReq addDepartmentReq, HttpServletRequest request) {
        service.addDepartment(addDepartmentReq,request);
    }

    @ApiOperation(value = "组织管理--修改部门", notes = "组织管理--修改部门")
    @ApiImplicitParam(name = "editDepartmentReq", value = "组织管理--修改部门", required = true, dataType = "EditDepartmentReq")
    @RequestMapping(value = "/editDepartment", method = RequestMethod.POST)
    public void editDepartment(@RequestBody EditDepartmentReq editDepartmentReq, HttpServletRequest request) {
        service.editDepartment(editDepartmentReq,request);
    }

    @ApiOperation(value = "组织管理--删除部门", notes = "组织管理--删除部门")
    @RequestMapping(value = "/deleteDepartment/{depId}", method = RequestMethod.GET)
    public void deleteDepartment(@PathVariable("depId") String depId, HttpServletRequest request) {
        service.deleteDepartment(depId,request);
    }
}
