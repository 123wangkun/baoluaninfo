package com.zl.school.business.controller.check;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.service.ICheckInfoService;
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
@RequestMapping("/check")
@Api(value = "知识管理后台API", description = "考核管理", tags = {"CheckInfoController"})
public class CheckInfoController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICheckInfoService service;

    @ApiOperation(value = "查询考核列表", notes = "查询考核列表")
    @ApiImplicitParam(name = "getCheckInfoListReq", value = "查询考核列表", required = true, dataType = "GetCheckInfoListReq")
    @RequestMapping(value = "/getCheckInfoList", method = RequestMethod.POST)
    public RspPage<GetCheckInfoListRes> getCheckInfoList(@RequestBody GetCheckInfoListReq getCheckInfoListReq, HttpServletRequest request) {
        return service.getCheckInfoList(getCheckInfoListReq,request);
    }

    @ApiOperation(value = "新增考核", notes = "新增考核")
    @ApiImplicitParam(name = "addCheckInfoReq", value = "新增考核", required = true, dataType = "AddCheckInfoReq")
    @RequestMapping(value = "/addCheckInfo", method = RequestMethod.POST)
    public void addCheckInfo(@RequestBody AddCheckInfoReq addCheckInfoReq, HttpServletRequest request) {
        service.addCheckInfo(addCheckInfoReq,request);
    }

    @ApiOperation(value = "修改考核", notes = "修改考核")
    @ApiImplicitParam(name = "editCheckInfoReq", value = "修改考核", required = true, dataType = "EditCheckInfoReq")
    @RequestMapping(value = "/editCheckInfo", method = RequestMethod.POST)
    public void editCheckInfo(@RequestBody EditCheckInfoReq editCheckInfoReq, HttpServletRequest request) {
        service.editCheckInfo(editCheckInfoReq,request);
    }

    @ApiOperation(value = "查询考核详情", notes = "查询考核详情")
    @RequestMapping(value = "/getCheckInfo/{id}", method = RequestMethod.GET)
    public GetCheckInfoRes getCheckInfo(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCheckInfo(id,request);
    }

    @ApiOperation(value = "删除考核", notes = "删除考核")
    @RequestMapping(value = "/deleteCheckInfo/{id}", method = RequestMethod.GET)
    public void deleteCheckInfo(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCheckInfo(id,request);
    }
    
}
