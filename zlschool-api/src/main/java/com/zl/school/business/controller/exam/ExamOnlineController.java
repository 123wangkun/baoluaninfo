package com.zl.school.business.controller.exam;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.GetExamOnlineUserReq;
import com.zl.school.business.dto.exam.GetExamOnlineUserRes;
import com.zl.school.business.service.IExamOnlineService;
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
@RequestMapping("/exam")
@Api(value = "知识管理后台API", description = "线上考试", tags = {"ExamOnlineController"})
public class ExamOnlineController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IExamOnlineService service;


    @ApiOperation(value = "查询线上考试已考用户列表", notes = "查询线下已考用户列表")
    @ApiImplicitParam(name = "getExamOnlineUserReq", value = "查询线下已考用户列表", required = true, dataType = "GetExamOnlineUserReq")
    @RequestMapping(value = "/getExamOnlineUser", method = RequestMethod.POST)
    public RspPage<GetExamOnlineUserRes> getExamOnlineUser(@RequestBody GetExamOnlineUserReq getExamOnlineUserReq, HttpServletRequest request) {
        return service.getExamOnlineUser(getExamOnlineUserReq,request);
    }
}
