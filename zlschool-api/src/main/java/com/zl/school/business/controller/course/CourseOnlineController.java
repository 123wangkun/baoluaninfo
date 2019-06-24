package com.zl.school.business.controller.course;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.service.ICourseOnlineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/course")
@Api(value = "知识管理后台API", description = "在线课程管理", tags = {"CourseOnlineController"})
public class CourseOnlineController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICourseOnlineService service;


    @ApiOperation(value = "查询在线课程列表", notes = "查询在线课程列表")
    @ApiImplicitParam(name = "getCourseOnlineListReq", value = "查询在线课程列表", required = true, dataType = "GetCourseOnlineListReq")
    @RequestMapping(value = "/getCourseOnlineList", method = RequestMethod.POST)
    public RspPage<GetCourseOnlineListRes> getCourseOnlineList(@RequestBody GetCourseOnlineListReq getCourseOnlineListReq, HttpServletRequest request) {
        return service.getCourseOnlineList(getCourseOnlineListReq,request);
    }

    @ApiOperation(value = "新增在线课程", notes = "新增在线课程")
    @ApiImplicitParam(name = "addCourseOnlineReq", value = "新增在线课程", required = true, dataType = "AddCourseOnlineReq")
    @RequestMapping(value = "/addCourseOnline", method = RequestMethod.POST)
    public void addCourseOnline(@RequestBody AddCourseOnlineReq addCourseOnlineReq, HttpServletRequest request) {
        service.addCourseOnline(addCourseOnlineReq,request);
    }

    @ApiOperation(value = "修改在线课程", notes = "修改在线课程")
    @ApiImplicitParam(name = "editCourseOnlineReq", value = "修改在线课程", required = true, dataType = "EditCourseOnlineReq")
    @RequestMapping(value = "/editCourseOnline", method = RequestMethod.POST)
    public void editCourseOnline(@RequestBody EditCourseOnlineReq editCourseOnlineReq, HttpServletRequest request) {
        service.editCourseOnline(editCourseOnlineReq,request);
    }

    @ApiOperation(value = "查询在线课程详情", notes = "查询在线课程详情")
    @RequestMapping(value = "/getCourseOnline/{id}", method = RequestMethod.GET)
    public GetCourseOnlineRes getCourseOnline(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCourseOnline(id,request);
    }

    @ApiOperation(value = "查询审核课程详情", notes = "查询审核课程详情")
    @RequestMapping(value = "/getProCourseOnline/{id}", method = RequestMethod.GET)
    public GetProCourseOnlineRes getProCourseOnline(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getProCourseOnline(id,request);
    }

    @ApiOperation(value = "删除在线课程", notes = "删除在线课程")
    @RequestMapping(value = "/deleteCourseOnline/{id}", method = RequestMethod.GET)
    public void deleteCourseOnline(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCourseOnline(id,request);
    }

    @ApiOperation(value = "查询在线活动列表", notes = "查询在线活动列表")
    @ApiImplicitParam(name = "getOnlineActivityListReq", value = "查询在线活动列表", required = true, dataType = "GetOnlineActivityListReq")
    @RequestMapping(value = "/selectOnlineActivityList", method = RequestMethod.POST)
    public RspPage<GetOnlineActivityListRes> selectOnlineActivityList(@RequestBody GetOnlineActivityListReq getOnlineActivityListReq, HttpServletRequest request) {
        return service.selectOnlineActivityList(getOnlineActivityListReq,request);
    }

    @ApiOperation(value = "在线课程审核", notes = "在线课程审核")
    @ApiImplicitParam(name = "proCourseOnlineReq", value = "在线课程审核", required = true, dataType = "ProCourseOnlineReq")
    @RequestMapping(value = "/proCourseOnline", method = RequestMethod.POST)
    public void proCourseOnline(@RequestBody ProCourseOnlineReq proCourseOnlineReq, HttpServletRequest request) {
        service.proCourseOnline(proCourseOnlineReq,request);
    }
}
