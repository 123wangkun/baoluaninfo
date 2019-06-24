package com.zl.school.business.dao.train;

import com.zl.school.business.dto.train.GetTrainTaskListRes;
import com.zl.school.business.entity.train.TrainTask;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface TrainTaskMapper extends BaseDao<TrainTask,String> {

    /**
     * 根据培训id删除培训任务
     * @return
     */
    void deleteTaskByTrainId(String trainId);
    /**
     * 根据培训id查询培训任务列表
     * @return
     */
    List<GetTrainTaskListRes> selectAllTaskByTrainId(String trainId);

}
