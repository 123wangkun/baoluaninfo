package com.zl.school.business.controller.train;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.train.*;
import com.zl.school.business.service.ITrainService;
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
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/train")
@Api(value = "知识管理后台API", description = "培训管理", tags = {"TrainController"})
public class TrainController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ITrainService service;


    @ApiOperation(value = "查询培训列表", notes = "查询培训列表")
    @ApiImplicitParam(name = "getTrainListReq", value = "查询培训列表", required = true, dataType = "GetTrainListReq")
    @RequestMapping(value = "/getTrainList", method = RequestMethod.POST)
    public RspPage<GetTrainListRes> getTrainList(@RequestBody GetTrainListReq getTrainListReq, HttpServletRequest request) {
        return service.getTrainList(getTrainListReq,request);
    }

    @ApiOperation(value = "新增培训", notes = "新增培训")
    @ApiImplicitParam(name = "addTrainReq", value = "新增培训", required = true, dataType = "AddTrainReq")
    @RequestMapping(value = "/addTrain", method = RequestMethod.POST)
    public void addTrain(@RequestBody AddTrainReq addTrainReq, HttpServletRequest request) {
        service.addTrain(addTrainReq,request);
    }

    @ApiOperation(value = "修改培训", notes = "修改培训")
    @ApiImplicitParam(name = "editTrainReq", value = "修改培训", required = true, dataType = "EditTrainReq")
    @RequestMapping(value = "/editTrain", method = RequestMethod.POST)
    public void editTrain(@RequestBody EditTrainReq editTrainReq, HttpServletRequest request) {
        service.editTrain(editTrainReq,request);
    }

    @ApiOperation(value = "查询培训详情", notes = "查询培训详情")
    @RequestMapping(value = "/getTrain/{id}", method = RequestMethod.GET)
    public GetTrainRes getTrain(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getTrain(id,request);
    }

    @ApiOperation(value = "删除培训", notes = "删除培训")
    @RequestMapping(value = "/deleteTrain/{id}", method = RequestMethod.GET)
    public void deleteTrain(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteTrain(id,request);
    }
}
