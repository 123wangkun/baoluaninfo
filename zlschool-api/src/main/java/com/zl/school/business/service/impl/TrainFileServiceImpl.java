package com.zl.school.business.service.impl;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.train.TrainFileMapper;
import com.zl.school.business.dto.train.AddTrainFileReq;
import com.zl.school.business.dto.train.EditTrainFileReq;
import com.zl.school.business.dto.train.GetTrainFileListRes;
import com.zl.school.business.dto.train.GetTrainFileRes;
import com.zl.school.business.entity.train.TrainFile;
import com.zl.school.business.service.ITrainFileService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class TrainFileServiceImpl extends AbstractServiceImpl<TrainFile,String> implements ITrainFileService {

    @Autowired
    private TrainFileMapper mapper;

    public TrainFileServiceImpl(TrainFileMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询培训资料列表
     * @return
     */
    @Override
    public List<GetTrainFileListRes> getTrainFileList(String trainId){
        //根据培训资料类型以及企业id查询所有培训资料列表
        List<GetTrainFileListRes> orders = mapper.selectAllFileByTrainId(trainId);
        return orders;
    }

    /**
     * 查询培训资料详情
     * @return
     */
    @Override
    public GetTrainFileRes getTrainFile(String id, HttpServletRequest request){
        TrainFile trainFile = null;
        GetTrainFileRes trainFileRes = null;
        //根据培训资料id查询培训资料基本信息
        trainFile = mapper.selectByPrimaryKey(id);
        if(trainFile != null){
            trainFileRes = new GetTrainFileRes();
            trainFileRes.setName(trainFile.getName());
            trainFileRes.setFileSize(trainFile.getFileSize());
            trainFileRes.setPosition(trainFile.getPosition());
            trainFileRes.setFileSrc(trainFile.getFileSrc());
            trainFileRes.setFileType(trainFile.getFileType());
            trainFileRes.setId(id);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询培训资料详情失败！");
        }
        return trainFileRes;
    }

    /**
     * 新增培训资料
     * @return
     */
    @Override
    public void addTrainFile(AddTrainFileReq addTrainFileReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        TrainFile trainFile = new TrainFile();
        trainFile.setId(UuidUtil.getRandomUuid());
        trainFile.setEid(eId);
        trainFile.setName(addTrainFileReq.getName());
        trainFile.setFileSize(addTrainFileReq.getFileSize());
        trainFile.setPosition(addTrainFileReq.getPosition());
        trainFile.setFileSrc(addTrainFileReq.getFileSrc());
        trainFile.setFileType(addTrainFileReq.getFileType());
        //添加数据到数据库
        mapper.insertSelective(trainFile);
    }

    /**
     * 修改培训资料
     * @return
     */
    @Override
    public void editTrainFile(EditTrainFileReq editTrainFileReq, HttpServletRequest request){
        TrainFile trainFile = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据培训资料id查询培训资料信息
        trainFile = mapper.selectByPrimaryKey(editTrainFileReq.getId());
        if(trainFile != null){
            trainFile.setName(editTrainFileReq.getName());
            trainFile.setFileSize(editTrainFileReq.getFileSize());
            trainFile.setPosition(editTrainFileReq.getPosition());
            trainFile.setFileSrc(editTrainFileReq.getFileSrc());
            trainFile.setFileType(editTrainFileReq.getFileType());
            //更新培训资料信息
            mapper.updateByPrimaryKeySelective(trainFile);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改培训资料信息失败！");
        }

    }

    /**
     * 删除培训资料
     * @return
     */
    @Override
    public void deleteTrainFile(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "培训资料编号不能为空！");
        }
        try {
            //根据id删除培训资料
            mapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }
    
}
