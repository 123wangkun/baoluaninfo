package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dao.train.TrainActivityMapper;
import com.zl.school.business.dao.train.TrainFileMapper;
import com.zl.school.business.dao.train.TrainTaskMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.train.*;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.entity.train.Train;
import com.zl.school.business.dao.train.TrainMapper;
import com.zl.school.business.service.ITrainService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.school.business.dto.train.AddTrainReq.AddTrainrLabelList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class TrainServiceImpl extends AbstractServiceImpl<Train,String> implements ITrainService {

    @Autowired
    private TrainMapper mapper;
    @Autowired
    private LabelMapper labMapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    @Autowired
    private TrainActivityMapper  trainActivityMapper;
    @Autowired
    private TrainTaskMapper trainTaskMapper;
    @Autowired
    private TrainFileMapper trainFileMapper;
    public TrainServiceImpl(TrainMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询培训列表
     * @return
     */
    @Override
    public RspPage<GetTrainListRes> getTrainList(GetTrainListReq getTrainListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getTrainListReq.setEId(eId);
        RspPage<GetTrainListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getTrainListReq.getPageNum(), getTrainListReq.getPageSize(), true);
        //根据企业id查询所有培训列表
        List<GetTrainListRes> orders = mapper.selectAllTrainByEid(getTrainListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询培训详情
     * @return
     */
    @Override
    public GetTrainRes getTrain(String id, HttpServletRequest request){
        Train train = null;
        GetTrainRes trainRes = null;
        //根据培训id查询培训基本信息
        train = mapper.selectByPrimaryKey(id);
        if(train != null){
            trainRes = new GetTrainRes();
            trainRes.setId(train.getId());
            trainRes.setStatus(train.getStatus());
            trainRes.setName(train.getName());
            trainRes.setPosition(train.getPosition());
            trainRes.setStatus(train.getStatus());
            trainRes.setStartTime(train.getStartTime());
            trainRes.setEndTime(train.getEndTime());
            trainRes.setIntegral(train.getIntegral());
            trainRes.setPosition(train.getPosition());
            String labelName = null;
            List<GetTrainRes.GetTrainLabelList> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(id);
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                GetTrainRes.GetTrainLabelList lab = new GetTrainRes.GetTrainLabelList();
                lab.setLabelId(labNameList.get(i).getId());
                labList.add(lab);
            }
            trainRes.setLabelIds(labList);
            trainRes.setLabelName(labelName);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询培训详情失败！");
        }
        return trainRes;
    }

    /**
     * 新增培训
     * @return
     */
    @Override
    public void addTrain(AddTrainReq addTrainReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Train train = new Train();
        train.setId(UuidUtil.getRandomUuid());
        train.setEid(eId);
        train.setStatus(train.getStatus());
        train.setName(train.getName());
        train.setPosition(train.getPosition());
        train.setStatus(train.getStatus());
        train.setStartTime(train.getStartTime());
        train.setEndTime(train.getEndTime());
        train.setIntegral(train.getIntegral());
        train.setCreatedTime(new Date());
        train.setCreateUser(userId);
        //添加培训记录
        mapper.insertSelective(train);
        //获取前端传过来的标签列表
        List<AddTrainrLabelList> labelIds = addTrainReq.getLabelIds();
        //循环保存关联信息到课程标签详情表
        for(AddTrainrLabelList lab : labelIds){
            LabelDetail labelDetail = new LabelDetail();
            labelDetail.setEid(eId);
            labelDetail.setId(UuidUtil.getRandomUuid());
            labelDetail.setLableId(lab.getLabelId());
            labelDetail.setRelationId(train.getId());
            labelDetail.setType(LabelDetail.train_type);
            labelDetail.setCreatedTime(new Date());
            labelDetail.setCreateUser(userId);
            //添加课程标签详情记录
            labelDetailMapper.insertSelective(labelDetail);
        }
    }

    /**
     * 修改培训
     * @return
     */
    @Override
    public void editTrain(EditTrainReq editTrainReq, HttpServletRequest request){
        Train train = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据培训id查询培训信息
        train = mapper.selectByPrimaryKey(editTrainReq.getId());
        if(train != null){
            train.setStatus(editTrainReq.getStatus());
            train.setName(editTrainReq.getName());
            train.setPosition(editTrainReq.getPosition());
            train.setStatus(editTrainReq.getStatus());
            train.setStartTime(editTrainReq.getStartTime());
            train.setEndTime(editTrainReq.getEndTime());
            train.setIntegral(editTrainReq.getIntegral());
            train.setUpdatedTime(new Date());
            train.setUpdateUser(userId);
            //更新培训信息
            mapper.updateByPrimaryKeySelective(train);

            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(editTrainReq.getId());
            //根据循环删除该课程关联的标签记录
            if(size>0){
                labelDetailMapper.deleteByRelationId(editTrainReq.getId());
            }
            //获取前端传过来的标签列表
            List<EditTrainReq.EditTrainLabelList> labelIds = editTrainReq.getLabelIds();
            //循环保存关联信息到课程标签详情表
            for(EditTrainReq.EditTrainLabelList label : labelIds){
                LabelDetail labDetail = new LabelDetail();
                labDetail.setEid(eId);
                labDetail.setId(UuidUtil.getRandomUuid());
                labDetail.setLableId(label.getLabelId());
                labDetail.setRelationId(train.getId());
                labDetail.setType(LabelDetail.train_type);
                labDetail.setCreatedTime(new Date());
                labDetail.setCreateUser(userId);
                //添加课程标签详情记录
                labelDetailMapper.insertSelective(labDetail);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改培训信息失败！");
        }

    }

    /**
     * 删除培训
     * @return
     */
    @Override
    public void deleteTrain(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "培训编号不能为空！");
        }
        try {
            //根据类型id查询该培训是否已关联用户
            int size = trainActivityMapper.selectCountTrain(id);
            if (size > 0) {
                throw new BusinessException(ErrorCode.ERROR_PHONE, "已有用户参与了培训，不能删除！");
            } else {
                //根据id删除培训
                mapper.deleteByPrimaryKey(id);
                //根据培训id删除培训任务
                trainTaskMapper.deleteTaskByTrainId(id);
                //根据培训id删除培训资料
                trainFileMapper.deleteFileByTrainId(id);
            }
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }


}
