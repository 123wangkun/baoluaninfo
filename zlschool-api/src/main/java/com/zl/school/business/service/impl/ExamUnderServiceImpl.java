package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.exam.ExamUnderDetailMapper;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.entity.exam.ExamUnder;
import com.zl.school.business.dao.exam.ExamUnderMapper;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.service.IExamUnderService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.school.business.dto.exam.GetExamUnderRes.GetExamUnderLabelList;
import com.zl.school.business.dto.exam.AddExamUnderReq.AddExamUnderLabelList;
import com.zl.school.business.dto.exam.EditExamUnderReq.EditExamUnderLabelList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class ExamUnderServiceImpl extends AbstractServiceImpl<ExamUnder,String> implements IExamUnderService {

    @Autowired
    private ExamUnderMapper mapper;
    @Autowired
    private LabelMapper labMapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    @Autowired
    private ExamUnderDetailMapper examUnderDetailMapper;

    public ExamUnderServiceImpl(ExamUnderMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询线下考试列表
     * @return
     */
    @Override
    public RspPage<GetExamUnderListRes> getExamUnderList(GetExamUnderListReq getCerTypeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getCerTypeListReq.setEId(eId);
        RspPage<GetExamUnderListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerTypeListReq.getPageNum(), getCerTypeListReq.getPageSize(), true);
        //根据企业id查询所有线下考试列表
        List<GetExamUnderListRes> orders = mapper.selectAllExamUnderByEid(getCerTypeListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询线下考试详情
     * @return
     */
    @Override
    public GetExamUnderRes getExamUnder(String id, HttpServletRequest request){
        ExamUnder examUnder = null;
        GetExamUnderRes examUnderRes = null;
        //根据线下考试id查询线下考试基本信息
        examUnder = mapper.selectByPrimaryKey(id);
        if(examUnder != null){
            examUnderRes = new GetExamUnderRes();
            examUnderRes.setId(examUnder.getId());
            examUnderRes.setAddress(examUnder.getAddress());
            examUnderRes.setName(examUnder.getName());
            examUnderRes.setPosition(examUnder.getPosition());
            examUnderRes.setState(examUnder.getState());
            examUnderRes.setQrcodeSrc(examUnder.getQrcodeSrc());
            examUnderRes.setStudyTime(examUnder.getStudyTime());
            examUnderRes.setTotalTime(examUnder.getTotalTime());
            String labelName = null;
            List<GetExamUnderLabelList> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(id);
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                GetExamUnderLabelList lab = new GetExamUnderLabelList();
                lab.setLabelId(labNameList.get(i).getId());
                labList.add(lab);
            }
            examUnderRes.setLabelName(labelName);
            examUnderRes.setLabelIds(labList);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询线下考试详情失败！");
        }
        return examUnderRes;
    }

    /**
     * 新增线下考试
     * @return
     */
    @Override
    public void addExamUnder(AddExamUnderReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        ExamUnder examUnder = new ExamUnder();
        examUnder.setId(UuidUtil.getRandomUuid());
        examUnder.setEid(eId);
        examUnder.setAddress(addCerTypeReq.getAddress());
        examUnder.setName(addCerTypeReq.getName());
        examUnder.setPosition(addCerTypeReq.getPosition());
        examUnder.setState(addCerTypeReq.getState());
        examUnder.setQrcodeSrc(addCerTypeReq.getQrcodeSrc());
        examUnder.setStudyTime(addCerTypeReq.getStudyTime());
        examUnder.setTotalTime(addCerTypeReq.getTotalTime());
        examUnder.setCreatedtTime(new Date());
        examUnder.setCreateUser(userId);
        //添加线下考试记录
        mapper.insertSelective(examUnder);

        //获取前端传过来的标签列表
        List<AddExamUnderLabelList> labelIds = addCerTypeReq.getLabelIds();
        //循环保存关联信息到课程标签详情表
        for(AddExamUnderLabelList lab : labelIds){
            LabelDetail labelDetail = new LabelDetail();
            labelDetail.setEid(eId);
            labelDetail.setId(UuidUtil.getRandomUuid());
            labelDetail.setLableId(lab.getLabelId());
            labelDetail.setRelationId(examUnder.getId());
            labelDetail.setType(LabelDetail.exam_type);
            labelDetail.setCreatedTime(new Date());
            labelDetail.setCreateUser(userId);
            //添加课程标签详情记录
            labelDetailMapper.insertSelective(labelDetail);
        }
    }

    /**
     * 修改线下考试
     * @return
     */
    @Override
    public void editExamUnder(EditExamUnderReq editCerTypeReq, HttpServletRequest request){
        ExamUnder examUnder = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据线下考试id查询线下考试信息
        examUnder = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(examUnder != null){
            examUnder.setAddress(editCerTypeReq.getAddress());
            examUnder.setName(editCerTypeReq.getName());
            examUnder.setPosition(editCerTypeReq.getPosition());
            examUnder.setQrcodeSrc(editCerTypeReq.getQrcodeSrc());
            examUnder.setStudyTime(editCerTypeReq.getStudyTime());
            examUnder.setTotalTime(editCerTypeReq.getTotalTime());
            examUnder.setState(editCerTypeReq.getState());
            examUnder.setUpdatedTime(new Date());
            examUnder.setUpdateUser(userId);
            //更新线下考试信息
            mapper.updateByPrimaryKeySelective(examUnder);

            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(editCerTypeReq.getId());
            //根据循环删除该课程关联的标签记录
            if(size>0){
                labelDetailMapper.deleteByRelationId(editCerTypeReq.getId());
            }
            //获取前端传过来的标签列表
            List<EditExamUnderLabelList> labelIds = editCerTypeReq.getLabelIds();
            //循环保存关联信息到课程标签详情表
            for(EditExamUnderLabelList label : labelIds){
                LabelDetail labDetail = new LabelDetail();
                labDetail.setEid(eId);
                labDetail.setId(UuidUtil.getRandomUuid());
                labDetail.setLableId(label.getLabelId());
                labDetail.setRelationId(examUnder.getId());
                labDetail.setType(LabelDetail.exam_type);
                labDetail.setCreatedTime(new Date());
                labDetail.setCreateUser(userId);
                //添加课程标签详情记录
                labelDetailMapper.insertSelective(labDetail);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改线下考试信息失败！");
        }

    }

    /**
     * 删除线下考试
     * @return
     */
    @Override
    public void deleteExamUnder(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "线下考试编号不能为空！");
        }
        //根据类型id查询该线下考试是否已关联用户
        int size = examUnderDetailMapper.selectCountExamUnder(id);
        if(size>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "已有用户考了该张试卷，不能删除！");
        }else {
            //根据id删除线下考试
            mapper.deleteByPrimaryKey(id);
        }
    }


    /**
     * 查询线下考试已考用户列表
     * @return
     */
    @Override
    public RspPage<GetExamUnderUserRes> getExamUnderUser(GetExamUnderUserReq examUnderUserReq, HttpServletRequest request){
        RspPage<GetExamUnderUserRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(examUnderUserReq.getPageNum(), examUnderUserReq.getPageSize(), true);
        //根据线下考试编号查询已考用户列表
        List<GetExamUnderUserRes> orders = mapper.selectExamUnderUserList(examUnderUserReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }
    
}
