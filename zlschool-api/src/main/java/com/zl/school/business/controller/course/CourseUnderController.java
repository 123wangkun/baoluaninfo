package com.zl.school.business.controller.course;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.service.ICourseUnderService;
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
@RequestMapping("/course")
@Api(value = "知识管理后台API", description = "线下课程管理", tags = {"CourseUnderController"})
public class CourseUnderController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICourseUnderService service;


    @ApiOperation(value = "查询线下课程列表", notes = "查询线下课程列表")
    @ApiImplicitParam(name = "getCourseUnderListReq", value = "查询线下课程列表", required = true, dataType = "GetCourseUnderListReq")
    @RequestMapping(value = "/getCourseUnderList", method = RequestMethod.POST)
    public RspPage<GetCourseUnderListRes> getCourseUnderList(@RequestBody GetCourseUnderListReq getCourseUnderListReq, HttpServletRequest request) {
        return service.getCourseUnderList(getCourseUnderListReq,request);
    }

    @ApiOperation(value = "新增线下课程", notes = "新增线下课程")
    @ApiImplicitParam(name = "addCourseUnderReq", value = "新增线下课程", required = true, dataType = "AddCourseUnderReq")
    @RequestMapping(value = "/addCourseUnder", method = RequestMethod.POST)
    public void addCourseUnder(@RequestBody AddCourseUnderReq addCourseUnderReq, HttpServletRequest request) {
        service.addCourseUnder(addCourseUnderReq,request);
    }

    @ApiOperation(value = "修改线下课程", notes = "修改线下课程")
    @ApiImplicitParam(name = "editCourseUnderReq", value = "修改线下课程", required = true, dataType = "EditCourseUnderReq")
    @RequestMapping(value = "/editCourseUnder", method = RequestMethod.POST)
    public void editCourseUnder(@RequestBody EditCourseUnderReq editCourseUnderReq, HttpServletRequest request) {
        service.editCourseUnder(editCourseUnderReq,request);
    }

    @ApiOperation(value = "查询线下课程详情", notes = "查询线下课程详情")
    @RequestMapping(value = "/getCourseUnder/{id}", method = RequestMethod.GET)
    public GetCourseUnderRes getCourseUnder(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCourseUnder(id,request);
    }

    @ApiOperation(value = "删除线下课程", notes = "删除线下课程")
    @RequestMapping(value = "/deleteCourseUnder/{id}", method = RequestMethod.GET)
    public void deleteCourseUnder(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCourseUnder(id,request);
    }

    @ApiOperation(value = "查询已学习用户列表", notes = "查询已学习用户列表")
    @ApiImplicitParam(name = "getCourseUnderUserListReq", value = "查询已学习用户列表", required = true, dataType = "GetCourseUnderUserListReq")
    @RequestMapping(value = "/selectStudyUserList", method = RequestMethod.POST)
    public RspPage<GetCourseUnderUserListRes> selectStudyUserList(@RequestBody GetCourseUnderUserListReq getCourseUnderUserListReq, HttpServletRequest request) {
        return service.selectStudyUserList(getCourseUnderUserListReq,request);
    }

    @ApiOperation(value = "线下课程审核", notes = "线下课程审核")
    @ApiImplicitParam(name = "proCourseUnderReq", value = "线下课程审核", required = true, dataType = "ProCourseUnderReq")
    @RequestMapping(value = "/proCourseUnder", method = RequestMethod.POST)
    public void proCourseUnder(@RequestBody ProCourseUnderReq proCourseUnderReq, HttpServletRequest request) {
        service.proCourseUnder(proCourseUnderReq,request);
    }
}
