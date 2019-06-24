package com.zl.school.business.dao.train;

import com.zl.school.business.dto.train.GetTrainListReq;
import com.zl.school.business.dto.train.GetTrainListRes;
import com.zl.school.business.entity.train.Train;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface TrainMapper extends BaseDao<Train,String> {
    /**
     * 查询企业下的所有培训列表
     * @return
     */
    List<GetTrainListRes> selectAllTrainByEid(GetTrainListReq getTrainListReq);

}
