package com.zl.school.business.dao.train;

import com.zl.school.business.entity.train.TrainActivity;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface TrainActivityMapper extends BaseDao<TrainActivity,String> {
    /**
     * 根据培训id查询统计总记录数
     * @return
     */
    int selectCountTrain(String trainId);

    /**
     * 根据任务id查询统计总记录数
     * @return
     */
    int selectCountTask(String taskId);
}
