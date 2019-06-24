package com.zl.school.business.controller.train;

import com.zl.school.business.dto.train.AddTrainFileReq;
import com.zl.school.business.dto.train.EditTrainFileReq;
import com.zl.school.business.dto.train.GetTrainFileListRes;
import com.zl.school.business.dto.train.GetTrainFileRes;
import com.zl.school.business.service.ITrainFileService;
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
@Api(value = "知识管理后台API", description = "培训资料管理", tags = {"TrainFileController"})
public class TrainFileController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ITrainFileService service;


    @ApiOperation(value = "根据培训id查询培训资料列表", notes = "根据培训id查询培训资料列表")
    @RequestMapping(value = "/getTrainFileList/{trainId}", method = RequestMethod.GET)
    public List<GetTrainFileListRes> getTrainFileList(@PathVariable("trainId") String trainId, HttpServletRequest request) {
        return service.getTrainFileList(trainId);
    }

    @ApiOperation(value = "新增培训资料", notes = "新增培训资料")
    @ApiImplicitParam(name = "addTrainFileReq", value = "新增培训资料", required = true, dataType = "AddTrainFileReq")
    @RequestMapping(value = "/addTrainFile", method = RequestMethod.POST)
    public void addTrainFile(@RequestBody AddTrainFileReq addTrainFileReq, HttpServletRequest request) {
        service.addTrainFile(addTrainFileReq,request);
    }

    @ApiOperation(value = "修改培训资料", notes = "修改培训资料")
    @ApiImplicitParam(name = "editTrainFileReq", value = "修改培训资料", required = true, dataType = "EditTrainFileReq")
    @RequestMapping(value = "/editTrainFile", method = RequestMethod.POST)
    public void editTrainFile(@RequestBody EditTrainFileReq editTrainFileReq, HttpServletRequest request) {
        service.editTrainFile(editTrainFileReq,request);
    }

    @ApiOperation(value = "查询培训资料详情", notes = "查询培训资料详情")
    @RequestMapping(value = "/getTrainFile/{id}", method = RequestMethod.GET)
    public GetTrainFileRes getTrainFile(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getTrainFile(id,request);
    }

    @ApiOperation(value = "删除培训资料", notes = "删除培训资料")
    @RequestMapping(value = "/deleteTrainFile/{id}", method = RequestMethod.GET)
    public void deleteTrainFile(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteTrainFile(id,request);
    }


}
