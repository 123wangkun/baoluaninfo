package com.zl.school.business.controller.exam;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.service.IPaperService;
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
@Api(value = "知识管理后台API", description = "试卷管理", tags = {"PaperController"})
public class PaperController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IPaperService service;

    @ApiOperation(value = "查询试卷列表", notes = "查询试卷列表")
    @ApiImplicitParam(name = "getPaperListReq", value = "查询试卷列表", required = true, dataType = "GetPaperListReq")
    @RequestMapping(value = "/getPaperList", method = RequestMethod.POST)
    public RspPage<GetPaperListRes> getPaperList(@RequestBody GetPaperListReq getPaperListReq, HttpServletRequest request) {
        return service.getPaperList(getPaperListReq,request);
    }

    @ApiOperation(value = "自动生成试卷", notes = "自动生成试卷")
    @ApiImplicitParam(name = "autoCreatePaperReq", value = "自动生成试卷", required = true, dataType = "AutoCreatePaperReq")
    @RequestMapping(value = "/autoCreatePaper", method = RequestMethod.POST)
    public void autoCreatePaper(@RequestBody AutoCreatePaperReq autoCreatePaperReq, HttpServletRequest request) {
        service.autoCreatePaper(autoCreatePaperReq,request);
    }

    @ApiOperation(value = "新增试卷", notes = "新增试卷")
    @ApiImplicitParam(name = "addPaperReq", value = "新增试卷", required = true, dataType = "AddPaperReq")
    @RequestMapping(value = "/addPaper", method = RequestMethod.POST)
    public void addPaper(@RequestBody AddPaperReq addPaperReq, HttpServletRequest request) {
        service.addPaper(addPaperReq,request);
    }

    @ApiOperation(value = "保存修改试卷信息", notes = "保存修改试卷信息")
    @ApiImplicitParam(name = "editPaperReq", value = "保存修改试卷信息", required = true, dataType = "EditPaperReq")
    @RequestMapping(value = "/editPaper", method = RequestMethod.POST)
    public void editPaper(@RequestBody EditPaperReq editPaperReq, HttpServletRequest request) {
        service.editPaper(editPaperReq,request);
    }

    @ApiOperation(value = "自动生成--修改试卷信息", notes = "自动生成--修改试卷信息")
    @ApiImplicitParam(name = "editAutoPaperReq", value = "自动生成--修改试卷信息", required = true, dataType = "EditAutoPaperReq")
    @RequestMapping(value = "/editAutoPaper", method = RequestMethod.POST)
    public void editAutoPaper(@RequestBody EditAutoPaperReq editAutoPaperReq, HttpServletRequest request) {
        service.editAutoPaper(editAutoPaperReq,request);
    }

    @ApiOperation(value = "查询试卷详情", notes = "查询试卷详情")
    @RequestMapping(value = "/getPaper/{id}", method = RequestMethod.GET)
    public GetPaperRes getPaper(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getPaper(id,request);
    }

    @ApiOperation(value = "删除试卷", notes = "删除试卷")
    @RequestMapping(value = "/deletePaper/{id}", method = RequestMethod.GET)
    public void deletePaper(@PathVariable("id") String id, HttpServletRequest request) {
        service.deletePaper(id,request);
    }
}
