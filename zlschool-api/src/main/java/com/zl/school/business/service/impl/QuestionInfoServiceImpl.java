package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dao.exam.PaperQuestionMapper;
import com.zl.school.business.dao.exam.QuestionOptionsMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.dto.label.GetQuestionReq.GetQuestionIds;
import com.zl.school.business.dto.label.GetQuestionInfoRes.QuestionLabIds;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.entity.exam.QuestionInfo;
import com.zl.school.business.dao.exam.QuestionInfoMapper;
import com.zl.school.business.entity.exam.QuestionOptions;
import com.zl.school.business.service.IQuestionInfoService;
import com.zl.school.business.dto.label.GetQuestionInfoRes.OptionsList;
import com.zl.school.business.dto.label.AddQuestionInfoReq.OptionsLists;
import com.zl.school.business.dto.label.AddQuestionInfoReq.QuestionLabelList;
import com.zl.school.business.dto.label.EditQuestionInfoReq.EditOptionsList;
import com.zl.school.business.dto.label.EditQuestionInfoReq.EditQuestionLabelList;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class QuestionInfoServiceImpl extends AbstractServiceImpl<QuestionInfo,String> implements IQuestionInfoService {

    @Autowired
    private QuestionInfoMapper mapper;
    @Autowired
    private LabelMapper labMapper;
    @Autowired
    private QuestionOptionsMapper questionOptionsMapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    public QuestionInfoServiceImpl(QuestionInfoMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询题目列表
     * @return
     */
    @Override
    public RspPage<GetQuestionInfoListRes> getQuestionInfoList(GetQuestionInfoListReq getCerTypeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getCerTypeListReq.setEId(eId);
        RspPage<GetQuestionInfoListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerTypeListReq.getPageNum(), getCerTypeListReq.getPageSize(), true);
        //根据题目类型以及企业id查询所有题目列表
        List<GetQuestionInfoListRes> orders = mapper.selectAllQuestionInfoByEid(getCerTypeListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询题目详情
     * @return
     */
    @Override
    public GetQuestionInfoRes getQuestionInfo(String id, HttpServletRequest request){
        QuestionInfo questionInfo = null;
        GetQuestionInfoRes questionInfoRes = null;
        //根据题目id查询题目基本信息
        questionInfo = mapper.selectByPrimaryKey(id);
        if(questionInfo != null){
            questionInfoRes = new GetQuestionInfoRes();
            questionInfoRes.setId(questionInfo.getId());
            questionInfoRes.setName(questionInfo.getName());
            questionInfoRes.setAnswer(questionInfo.getAnswer());
            questionInfoRes.setScore(questionInfo.getScore());
            questionInfoRes.setType(questionInfo.getType());
            questionInfoRes.setImage(questionInfo.getImage());
            questionInfoRes.setMemo(questionInfo.getMemo());
            //1审核中;2进行中;3已结束;4未启用;
            switch(questionInfo.getType()){
                case 1:
                    questionInfoRes.setTypeName("单选题");
                    break;
                case 2:
                    questionInfoRes.setTypeName("多选题");
                    break;
                case 3:
                    questionInfoRes.setTypeName("判断题");
                    break;
            }
            String labelName = null;
            List<QuestionLabIds> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(id);
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                QuestionLabIds lab = new QuestionLabIds();
                lab.setId(labNameList.get(i).getId());
                labList.add(lab);
            }
            questionInfoRes.setLabIds(labList);
            questionInfoRes.setLabelLists(labelName);
            //根据题目id查询该题目下的所有选项
            List<QuestionOptions> opLists = questionOptionsMapper.selectByQuestionId(id);
            List<OptionsList> optionsLists=new ArrayList<>();
            //循环读取选项内容并放进DTO返回
            for(QuestionOptions options : opLists){
                OptionsList list = new OptionsList();
                list.setOptionsId(options.getOptionsId());
                list.setContent(options.getContent());
                list.setId(options.getId());
                optionsLists.add(list);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询题目详情失败！");
        }
        return questionInfoRes;
    }

    /**
     * 新增题目
     * @return
     */
    @Override
    public void addQuestionInfo(AddQuestionInfoReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setId(UuidUtil.getRandomUuid());
        questionInfo.setEid(eId);
        questionInfo.setName(addCerTypeReq.getName());
        questionInfo.setAnswer(addCerTypeReq.getAnswer());
        questionInfo.setScore(addCerTypeReq.getScore());
        questionInfo.setType(addCerTypeReq.getType());
        questionInfo.setImage(addCerTypeReq.getImage());
        questionInfo.setMemo(addCerTypeReq.getMemo());
        questionInfo.setCreatedTime(new Date());
        questionInfo.setCreateUser(userId);
        //将数据保存到题目表
        mapper.insertSelective(questionInfo);
        //将界面传过来的选项列表循环保存到数据表
        for(OptionsLists options : addCerTypeReq.getOptionsList()){
            QuestionOptions questionOptions = new QuestionOptions();
            questionOptions.setOptionsId(options.getOptionsId());
            questionOptions.setContent(options.getContent());
            questionOptions.setEid(eId);
            questionOptions.setQuestionId(questionInfo.getId());
            questionOptions.setId(UuidUtil.getRandomUuid());
            questionOptionsMapper.insertSelective(questionOptions);
        }
        //循环保存关联信息到课程标签详情表
        for(QuestionLabelList lab : addCerTypeReq.getLabelIds()){
            LabelDetail labelDetail = new LabelDetail();
            labelDetail.setEid(eId);
            labelDetail.setId(UuidUtil.getRandomUuid());
            labelDetail.setLableId(lab.getLabelId());
            labelDetail.setRelationId(questionInfo.getId());
            labelDetail.setType(LabelDetail.question_type);
            labelDetail.setCreatedTime(new Date());
            labelDetail.setCreateUser(userId);
            //添加题目标签详情记录
            labelDetailMapper.insertSelective(labelDetail);
        }
    }

    /**
     * 修改题目
     * @return
     */
    @Override
    public void editQuestionInfo(EditQuestionInfoReq editCerTypeReq, HttpServletRequest request){
        QuestionInfo questionInfo = null;
        //获取当前用户的id以及所属企业id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据题目id查询题目信息
        questionInfo = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(questionInfo != null){
            questionInfo.setId(editCerTypeReq.getId());
            questionInfo.setName(editCerTypeReq.getName());
            questionInfo.setAnswer(editCerTypeReq.getAnswer());
            questionInfo.setScore(editCerTypeReq.getScore());
            questionInfo.setType(editCerTypeReq.getType());
            questionInfo.setImage(editCerTypeReq.getImage());
            questionInfo.setMemo(editCerTypeReq.getMemo());
            questionInfo.setUpdatedTime(new Date());
            questionInfo.setUpdateUser(userId);
            //更新题目信息
            mapper.updateByPrimaryKeySelective(questionInfo);
            //将界面传过来的选项列表循环保存到数据表
            for(EditOptionsList options : editCerTypeReq.getOptionsList()){
                QuestionOptions questionOptions = new QuestionOptions();
                questionOptions.setOptionsId(options.getOptionsId());
                questionOptions.setContent(options.getContent());
                questionOptions.setId(options.getId());
                questionOptionsMapper.updateByPrimaryKeySelective(questionOptions);
            }
            //查询统计该题目下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(editCerTypeReq.getId());
            //根据题目id循环删除该题目关联的标签记录
            if(size>0){
                labelDetailMapper.deleteByRelationId(editCerTypeReq.getId());
            }
            //获取前端传过来的标签列表
            List<EditQuestionLabelList> labelIds = editCerTypeReq.getLabelIds();
            //循环保存关联信息到题目标签详情表
            for(EditQuestionLabelList label : labelIds){
                LabelDetail labDetail = new LabelDetail();
                labDetail.setEid(eId);
                labDetail.setId(UuidUtil.getRandomUuid());
                labDetail.setRelationId(editCerTypeReq.getId());
                labDetail.setLableId(label.getLabelId());
                labDetail.setType(LabelDetail.question_type);
                labDetail.setCreatedTime(new Date());
                labDetail.setCreateUser(userId);
                //添加课程标签详情记录
                labelDetailMapper.insertSelective(labDetail);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改题目信息失败！");
        }

    }

    /**
     * 删除题目
     * @return
     */
    @Override
    public void deleteQuestionInfo(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "题目编号不能为空！");
        }
        //查询统计该题目共关联多少试卷
        int size = paperQuestionMapper.selectCountByQuestion(id);
        if(size>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该题目已绑定试卷，不能删除！！");
        }else {
            //根据id删除题目
            mapper.deleteByPrimaryKey(id);
            //根据题目id删除对应选项
            int number = questionOptionsMapper.selectOptionsByQuestion(id);
            if(number>0){
                //删除对应选项记录
                questionOptionsMapper.deleteByQuestion(id);
            }
            //查询统计该题目下的共关联多少标签
            int labSize = labelDetailMapper.isExistByLabelDetail(id);
            //根据题目id循环删除该题目关联的标签记录
            if(labSize>0){
                labelDetailMapper.deleteByRelationId(id);
            }
        }
    }

    /**
     * 根据标签查询题目列表
     * @return
     */
    @Override
    public RspPage<GetQuestionListRes> getQuestionListByLabel(GetQuestionReq getQuestionReq, HttpServletRequest request){
        if(getQuestionReq.getType() == 0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "题目类型不能为空！");
        }
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getQuestionReq.setEId(eId);
        RspPage<GetQuestionListRes> rspPage = new RspPage<>();
        String labelIds = null;
        //获取前端传过来的标签id
        List<GetQuestionIds> labIdList = getQuestionReq.getLabIds();
        //将标签列表循环进行拼接，用逗号隔开
        for(int i=0;i<labIdList.size();i++){
            labelIds += labIdList.get(i).getId()+",";
        }
        getQuestionReq.setLabs(labelIds);
        //初始化分页
        Page page = PageHelper.startPage(getQuestionReq.getPageNum(), getQuestionReq.getPageSize(), true);
        //根据题目类型以及企业id查询所有题目列表
        List<GetQuestionListRes> orders = mapper.selectQuestionListByLabel(getQuestionReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;

    }
}
