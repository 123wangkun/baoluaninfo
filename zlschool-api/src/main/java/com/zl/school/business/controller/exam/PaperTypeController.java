package com.zl.school.business.controller.exam;

import com.zl.school.business.dto.exam.AddPaperTypeReq;
import com.zl.school.business.dto.exam.EditPaperTypeReq;
import com.zl.school.business.dto.exam.GetPaperTypeListRes;
import com.zl.school.business.dto.exam.GetPaperTypeRes;
import com.zl.school.business.service.IPaperTypeService;
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
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/exam")
@Api(value = "知识管理后台API", description = "试卷类型管理", tags = {"PaperTypeController"})
public class PaperTypeController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IPaperTypeService service;

    @ApiOperation(value = "查询试卷类型列表", notes = "查询试卷类型列表")
    @ApiImplicitParam(name = "getCerTypeListReq", value = "查询试卷类型列表", required = true, dataType = "GetPaperTypeListReq")
    @RequestMapping(value = "/getPaperTypeList", method = RequestMethod.POST)
    public List<GetPaperTypeListRes> getPaperTypeList(HttpServletRequest request) {
        return service.getPaperTypeList(request);
    }

    @ApiOperation(value = "新增试卷类型", notes = "新增试卷类型")
    @ApiImplicitParam(name = "addPaperTypeReq", value = "新增试卷类型", required = true, dataType = "AddPaperTypeReq")
    @RequestMapping(value = "/addPaperType", method = RequestMethod.POST)
    public void addPaperType(@RequestBody AddPaperTypeReq addPaperTypeReq, HttpServletRequest request) {
        service.addPaperType(addPaperTypeReq,request);
    }

    @ApiOperation(value = "修改试卷类型", notes = "修改试卷类型")
    @ApiImplicitParam(name = "editPaperTypeReq", value = "修改试卷类型", required = true, dataType = "EditPaperTypeReq")
    @RequestMapping(value = "/editPaperType", method = RequestMethod.POST)
    public void editPaperType(@RequestBody EditPaperTypeReq editPaperTypeReq, HttpServletRequest request) {
        service.editPaperType(editPaperTypeReq,request);
    }

    @ApiOperation(value = "查询试卷类型详情", notes = "查询试卷类型详情")
    @RequestMapping(value = "/getPaperType/{id}", method = RequestMethod.GET)
    public GetPaperTypeRes getPaperType(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getPaperType(id,request);
    }

    @ApiOperation(value = "删除试卷类型", notes = "删除试卷类型")
    @RequestMapping(value = "/deletePaperType/{id}", method = RequestMethod.GET)
    public void deletePaperType(@PathVariable("id") String id, HttpServletRequest request) {
        service.deletePaperType(id,request);
    }
}
