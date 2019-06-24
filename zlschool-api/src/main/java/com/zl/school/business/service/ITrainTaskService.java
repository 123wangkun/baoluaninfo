package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.train.*;
import com.zl.school.business.entity.train.TrainTask;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ITrainTaskService extends IBaseService<TrainTask,String> {

    /**
     * 查询培训任务列表
     * @return
     */
    List<GetTrainTaskListRes> getTrainTaskList(String trainId);

    /**
     * 查询培训任务详情
     * @return
     */
    GetTrainTaskRes getTrainTask(String id, HttpServletRequest request);

    /**
     * 新增培训任务
     * @return
     */
    void addTrainTask(AddTrainTaskReq addTrainTaskReq, HttpServletRequest request);

    /**
     * 修改培训任务
     * @return
     */
    void editTrainTask(EditTrainTaskReq editTrainTaskReq, HttpServletRequest request);

    /**
     * 删除培训任务
     * @return
     */
    void deleteTrainTask(String id, HttpServletRequest request);


}
