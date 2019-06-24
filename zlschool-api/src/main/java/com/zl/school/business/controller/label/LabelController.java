package com.zl.school.business.controller.label;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/label")
@Api(value = "知识管理后台API", description = "标签管理", tags = {"LabelController"})
public class LabelController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ILabelService service;

    @ApiOperation(value = "根据企业id查询标签列表", notes = "根据企业id查询标签列表")
    @ApiImplicitParam(name = "getLabelListReq", value = "根据企业id查询标签列表", required = true, dataType = "GetLabelListReq")
    @RequestMapping(value = "/getLabelList", method = RequestMethod.POST)
    public RspPage<GetLabelListRes> getLabelList(@RequestBody GetLabelListReq getLabelListReq, HttpServletRequest request) {
        return service.getLabelList(getLabelListReq,request);
    }

    @ApiOperation(value = "查询标签目录树", notes = "查询标签目录树")
    @RequestMapping(value = "/getLabelTree", method = RequestMethod.POST)
    public GetLabelTreeRes getLabelTree(HttpServletRequest request) {
        return service.getLabelTree(request);
    }

    @ApiOperation(value = "新增标签", notes = "新增标签")
    @ApiImplicitParam(name = "addLabelReq", value = "新增标签", required = true, dataType = "AddLabelReq")
    @RequestMapping(value = "/addLabel", method = RequestMethod.POST)
    public void addLabel(@RequestBody AddLabelReq addLabelReq, HttpServletRequest request) {
        service.addLabel(addLabelReq,request);
    }

    @ApiOperation(value = "修改标签", notes = "修改标签")
    @ApiImplicitParam(name = "editLabelReq", value = "修改标签", required = true, dataType = "EditLabelReq")
    @RequestMapping(value = "/editLabel", method = RequestMethod.POST)
    public void editLabel(@RequestBody EditLabelReq editLabelReq, HttpServletRequest request) {
        service.editLabel(editLabelReq,request);
    }

    @ApiOperation(value = "查询标签详情", notes = "查询标签详情")
    @RequestMapping(value = "/getLabel/{id}", method = RequestMethod.POST)
    public GetLabelRes getLabel(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getLabel(id,request);
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @RequestMapping(value = "/deleteLabel/{id}", method = RequestMethod.POST)
    public void deleteLabel(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteLabel(id,request);
    }

}
