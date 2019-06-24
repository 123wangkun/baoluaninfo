package com.zl.school.business.service;

import com.zl.school.business.dto.train.AddTrainFileReq;
import com.zl.school.business.dto.train.EditTrainFileReq;
import com.zl.school.business.dto.train.GetTrainFileListRes;
import com.zl.school.business.dto.train.GetTrainFileRes;
import com.zl.school.business.entity.train.TrainFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ITrainFileService extends IBaseService<TrainFile,String> {

    /**
     * 查询培训资料列表
     * @return
     */
    List<GetTrainFileListRes> getTrainFileList(String trainId);

    /**
     * 查询培训资料详情
     * @return
     */
    GetTrainFileRes getTrainFile(String id, HttpServletRequest request);

    /**
     * 新增培训资料
     * @return
     */
    void addTrainFile(AddTrainFileReq addTrainFileReq, HttpServletRequest request);

    /**
     * 修改培训资料
     * @return
     */
    void editTrainFile(EditTrainFileReq editTrainFileReq, HttpServletRequest request);

    /**
     * 删除培训资料
     * @return
     */
    void deleteTrainFile(String id, HttpServletRequest request);



}
