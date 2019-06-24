package com.zl.school.business.controller.train;

import com.zl.school.business.dto.train.*;
import com.zl.school.business.service.ITrainTaskService;
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
@RequestMapping("/train")
@Api(value = "知识管理后台API", description = "培训任务管理", tags = {"TrainTaskTaskController"})
public class TrainTaskController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ITrainTaskService service;

    @ApiOperation(value = "根据培训id查询培训任务列表", notes = "根据培训id查询培训任务列表")
    @RequestMapping(value = "/getTrainTaskList/{trainId}", method = RequestMethod.GET)
    public List<GetTrainTaskListRes> getTrainTaskList(@PathVariable("trainId") String trainId,HttpServletRequest request) {
        return service.getTrainTaskList(trainId);
    }

    @ApiOperation(value = "新增培训任务", notes = "新增培训任务")
    @ApiImplicitParam(name = "addTrainTaskReq", value = "新增培训任务", required = true, dataType = "AddTrainTaskReq")
    @RequestMapping(value = "/addTrainTask", method = RequestMethod.POST)
    public void addTrainTask(@RequestBody AddTrainTaskReq addTrainTaskReq, HttpServletRequest request) {
        service.addTrainTask(addTrainTaskReq,request);
    }

    @ApiOperation(value = "修改培训任务", notes = "修改培训任务")
    @ApiImplicitParam(name = "editTrainTaskReq", value = "修改培训任务", required = true, dataType = "EditTrainTaskReq")
    @RequestMapping(value = "/editTrainTask", method = RequestMethod.POST)
    public void editTrainTask(@RequestBody EditTrainTaskReq editTrainTaskReq, HttpServletRequest request) {
        service.editTrainTask(editTrainTaskReq,request);
    }

    @ApiOperation(value = "查询培训任务详情", notes = "查询培训任务详情")
    @RequestMapping(value = "/getTrainTask/{id}", method = RequestMethod.GET)
    public GetTrainTaskRes getTrainTask(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getTrainTask(id,request);
    }

    @ApiOperation(value = "删除培训任务", notes = "删除培训任务")
    @RequestMapping(value = "/deleteTrainTask/{id}", method = RequestMethod.GET)
    public void deleteTrainTask(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteTrainTask(id,request);
    }
    
}
