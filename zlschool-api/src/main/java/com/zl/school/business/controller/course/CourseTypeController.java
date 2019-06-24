package com.zl.school.business.controller.course;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.service.ICourseTypeService;
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
@Api(value = "知识管理后台API", description = "课程类型管理", tags = {"CourseTypeController"})
public class CourseTypeController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICourseTypeService service;

    @ApiOperation(value = "查询课程类型列表", notes = "查询课程类型列表")
    @ApiImplicitParam(name = "getCourseTypeListReq", value = "查询课程类型列表", required = true, dataType = "GetCourseTypeListReq")
    @RequestMapping(value = "/getCourseTypeList", method = RequestMethod.POST)
    public RspPage<GetCourseTypeListRes> getCourseTypeList(@RequestBody GetCourseTypeListReq getCourseTypeListReq, HttpServletRequest request) {
        return service.getCourseTypeList(getCourseTypeListReq,request);
    }

    @ApiOperation(value = "查询课程详情", notes = "查询课程详情")
    @RequestMapping(value = "/getCourseType/{id}", method = RequestMethod.POST)
    public GetCourseTypeRes getCourseType(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCourseType(id,request);
    }

    @ApiOperation(value = "新增课程类型", notes = "新增课程类型")
    @ApiImplicitParam(name = "addCourseTypeReq", value = "新增用户", required = true, dataType = "AddCourseTypeReq")
    @RequestMapping(value = "/addCourseType", method = RequestMethod.POST)
    public void addCourseType(@RequestBody AddCourseTypeReq addCourseTypeReq, HttpServletRequest request) {
        service.addCourseType(addCourseTypeReq,request);
    }

    @ApiOperation(value = "修改课程类型", notes = "修改课程类型")
    @ApiImplicitParam(name = "editCourseTypeReq", value = "修改课程类型", required = true, dataType = "EditCourseTypeReq")
    @RequestMapping(value = "/editCourseType", method = RequestMethod.POST)
    public void editCourseType(@RequestBody EditCourseTypeReq editCourseTypeReq, HttpServletRequest request) {
        service.editCourseType(editCourseTypeReq,request);
    }


    @ApiOperation(value = "删除课程类型", notes = "删除课程类型")
    @RequestMapping(value = "/deleteCourseType/{id}", method = RequestMethod.GET)
    public void deleteCourseType(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCourseType(id,request);
    }
}
