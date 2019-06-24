package com.zl.school.business.controller.exam;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.service.IExamUnderService;
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
@RequestMapping("/exam")
@Api(value = "知识管理后台API", description = "线下考试", tags = {"ExamUnderController"})
public class ExamUnderController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IExamUnderService service;


    @ApiOperation(value = "查询线下考试列表", notes = "查询线下考试列表")
    @ApiImplicitParam(name = "getExamUnderListReq", value = "查询线下考试列表", required = true, dataType = "GetExamUnderListReq")
    @RequestMapping(value = "/getExamUnderList", method = RequestMethod.POST)
    public RspPage<GetExamUnderListRes> getExamUnderList(@RequestBody GetExamUnderListReq getExamUnderListReq, HttpServletRequest request) {
        return service.getExamUnderList(getExamUnderListReq,request);
    }

    @ApiOperation(value = "新增线下考试", notes = "新增线下考试")
    @ApiImplicitParam(name = "addExamUnderReq", value = "新增线下考试", required = true, dataType = "AddExamUnderReq")
    @RequestMapping(value = "/addExamUnder", method = RequestMethod.POST)
    public void addExamUnder(@RequestBody AddExamUnderReq addExamUnderReq, HttpServletRequest request) {
        service.addExamUnder(addExamUnderReq,request);
    }

    @ApiOperation(value = "修改线下考试", notes = "修改线下考试")
    @ApiImplicitParam(name = "editExamUnderReq", value = "修改线下考试", required = true, dataType = "EditExamUnderReq")
    @RequestMapping(value = "/editExamUnder", method = RequestMethod.POST)
    public void editExamUnder(@RequestBody EditExamUnderReq editExamUnderReq, HttpServletRequest request) {
        service.editExamUnder(editExamUnderReq,request);
    }

    @ApiOperation(value = "查询线下考试详情", notes = "查询线下考试详情")
    @RequestMapping(value = "/getExamUnder/{id}", method = RequestMethod.GET)
    public GetExamUnderRes getExamUnder(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getExamUnder(id,request);
    }

    @ApiOperation(value = "删除线下考试", notes = "删除线下考试")
    @RequestMapping(value = "/deleteExamUnder/{id}", method = RequestMethod.GET)
    public void deleteExamUnder(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteExamUnder(id,request);
    }

    @ApiOperation(value = "查询线下已考用户列表", notes = "查询线下已考用户列表")
    @ApiImplicitParam(name = "getExamUnderUserReq", value = "查询线下已考用户列表", required = true, dataType = "GetExamUnderUserReq")
    @RequestMapping(value = "/getExamUnderUser", method = RequestMethod.POST)
    public RspPage<GetExamUnderUserRes> getExamUnderUser(@RequestBody GetExamUnderUserReq getExamUnderUserReq, HttpServletRequest request) {
        return service.getExamUnderUser(getExamUnderUserReq,request);
    }
}
