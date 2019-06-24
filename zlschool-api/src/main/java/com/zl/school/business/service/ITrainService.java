package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.train.*;
import com.zl.school.business.entity.train.Train;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ITrainService extends IBaseService<Train,String> {

    /**
     * 查询培训列表
     * @return
     */
    RspPage<GetTrainListRes> getTrainList(GetTrainListReq getTrainListReq, HttpServletRequest request);

    /**
     * 查询培训详情
     * @return
     */
    GetTrainRes getTrain(String id, HttpServletRequest request);

    /**
     * 新增培训
     * @return
     */
    void addTrain(AddTrainReq addTrainReq, HttpServletRequest request);

    /**
     * 修改培训
     * @return
     */
    void editTrain(EditTrainReq editTrainReq, HttpServletRequest request);

    /**
     * 删除培训
     * @return
     */
    void deleteTrain(String id, HttpServletRequest request);
    
}
