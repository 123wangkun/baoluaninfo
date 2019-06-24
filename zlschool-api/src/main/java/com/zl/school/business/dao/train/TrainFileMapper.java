package com.zl.school.business.dao.train;

import com.zl.school.business.dto.train.GetTrainFileListRes;
import com.zl.school.business.dto.train.GetTrainTaskListRes;
import com.zl.school.business.entity.train.TrainFile;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface TrainFileMapper extends BaseDao<TrainFile,String> {
    /**
     * 根据培训id删除培训资料
     * @return
     */
    void deleteFileByTrainId(String trainId);

    /**
     * 根据培训id查询培训资料列表
     * @return
     */
    List<GetTrainFileListRes> selectAllFileByTrainId(String trainId);
}
