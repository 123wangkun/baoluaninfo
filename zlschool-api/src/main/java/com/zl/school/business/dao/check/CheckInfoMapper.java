package com.zl.school.business.dao.check;

import com.zl.school.business.dto.check.GetCheckInfoListReq;
import com.zl.school.business.dto.check.GetCheckInfoListRes;
import com.zl.school.business.dto.train.GetTrainListReq;
import com.zl.school.business.dto.train.GetTrainListRes;
import com.zl.school.business.entity.check.CheckInfo;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CheckInfoMapper extends BaseDao<CheckInfo,String> {

    /**
     * 查询企业下的所有考核列表
     * @return
     */
    List<GetCheckInfoListRes> selectAllCheckInfoByEid(GetCheckInfoListReq getReq);

}
