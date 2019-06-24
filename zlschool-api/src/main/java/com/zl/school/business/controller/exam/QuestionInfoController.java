package com.zl.school.business.controller.exam;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.service.IQuestionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/exam")
@Api(value = "知识管理后台API", description = "题目管理", tags = {"QuestionInfoController"})
public class QuestionInfoController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(QuestionInfoController.class);
    @Autowired
    private IQuestionInfoService service;


    @ApiOperation(value = "查询题目列表", notes = "查询题目列表")
    @ApiImplicitParam(name = "getQuestionInfoListReq", value = "查询题目列表", required = true, dataType = "GetQuestionInfoListReq")
    @RequestMapping(value = "/getQuestionInfoList", method = RequestMethod.POST)
    public RspPage<GetQuestionInfoListRes> getQuestionInfoList(@RequestBody GetQuestionInfoListReq getQuestionInfoListReq, HttpServletRequest request) {
        return service.getQuestionInfoList(getQuestionInfoListReq,request);
    }

    @ApiOperation(value = "新增题目", notes = "新增题目")
    @ApiImplicitParam(name = "addQuestionInfoReq", value = "新增题目", required = true, dataType = "AddQuestionInfoReq")
    @RequestMapping(value = "/addQuestionInfo", method = RequestMethod.POST)
    public void addQuestionInfo(@RequestBody AddQuestionInfoReq addQuestionInfoReq, HttpServletRequest request) {
        service.addQuestionInfo(addQuestionInfoReq,request);
    }

    @ApiOperation(value = "修改题目", notes = "修改题目")
    @ApiImplicitParam(name = "editQuestionInfoReq", value = "修改题目", required = true, dataType = "EditQuestionInfoReq")
    @RequestMapping(value = "/editQuestionInfo", method = RequestMethod.POST)
    public void editQuestionInfo(@RequestBody EditQuestionInfoReq editQuestionInfoReq, HttpServletRequest request) {
        service.editQuestionInfo(editQuestionInfoReq,request);
    }

    @ApiOperation(value = "查询题目详情", notes = "查询题目详情")
    @RequestMapping(value = "/getQuestionInfo/{id}", method = RequestMethod.GET)
    public GetQuestionInfoRes getQuestionInfo(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getQuestionInfo(id,request);
    }

    @ApiOperation(value = "删除题目", notes = "删除题目")
    @RequestMapping(value = "/deleteQuestionInfo/{id}", method = RequestMethod.GET)
    public void deleteQuestionInfo(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteQuestionInfo(id,request);
    }

    @ApiOperation(value = "新增试卷--根据标签查询题目列表", notes = "新增试卷--根据标签查询题目列表")
    @ApiImplicitParam(name = "getQuestionReq", value = "新增试卷--根据标签查询题目列表", required = true, dataType = "GetQuestionReq")
    @RequestMapping(value = "/getQuestionListByLabel", method = RequestMethod.POST)
    public RspPage<GetQuestionListRes> getQuestionListByLabel(@RequestBody GetQuestionReq getQuestionReq, HttpServletRequest request) {
        return service.getQuestionListByLabel(getQuestionReq,request);
    }
}
