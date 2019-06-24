package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.exam.ExamOnlineMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dao.exam.PaperQuestionMapper;
import com.zl.school.business.dao.exam.QuestionInfoMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.exam.Paper;
import com.zl.school.business.dao.exam.PaperMapper;
import com.zl.school.business.dto.exam.AddPaperReq.QuestionList;
import com.zl.school.business.dto.exam.EditPaperReq.EditQuestionList;
import com.zl.school.business.dto.exam.EditAutoPaperReq.EditAutoLabIds;
import com.zl.school.business.entity.exam.PaperQuestion;
import com.zl.school.business.entity.exam.QuestionInfo;
import com.zl.school.business.service.IPaperService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.school.business.dto.exam.GetPaperRes.LabIds;
import com.zl.school.business.dto.exam.AutoCreatePaperReq.AutoLabIds;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class PaperServiceImpl extends AbstractServiceImpl<Paper,String> implements IPaperService {

    @Autowired
    private PaperMapper mapper;
    @Autowired
    private ExamOnlineMapper examOnlineMapper;
    @Autowired
    private LabelMapper labMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;
    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    public PaperServiceImpl(PaperMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询试卷列表
     * @return
     */
    @Override
    public RspPage<GetPaperListRes> getPaperList(GetPaperListReq getCerListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getCerListReq.setEId(eId);
        RspPage<GetPaperListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerListReq.getPageNum(), getCerListReq.getPageSize(), true);
        List<GetPaperListRes> orders = mapper.selectAllPaperByEid(getCerListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }


    /**
     * 查询试卷详情
     * @return
     */
    @Override
    public GetPaperRes getPaper(String id, HttpServletRequest request){
        Paper paper = null;
        GetPaperRes paperRes = null;
        //根据试卷id查询试卷基本信息
        paper = mapper.selectByPrimaryKey(id);
        if(paper != null){
            paperRes = new GetPaperRes();
            paperRes.setId(paper.getId());
            paperRes.setMemo(paper.getMemo());
            paperRes.setName(paper.getName());
            paperRes.setTypeId(paper.getTypeId());
            paperRes.setTotalScore(paper.getTotalScore());
            paperRes.setPassLine(paper.getPassLine());
            paperRes.setSpendTime(paper.getSpendTime());
            paperRes.setStartStatus(paper.getStartStatus());
            paperRes.setReleaseStatus(paper.getReleaseStatus());
            paperRes.setIntegral(paper.getIntegral());
            paperRes.setSingle(paper.getSingle());
            paperRes.setMultiple(paper.getMultiple());
            paperRes.setJudge(paper.getJudge());
            String labelName = null;
            List<LabIds> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(id);
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                LabIds lab = new LabIds();
                lab.setId(labNameList.get(i).getId());
                labList.add(lab);
            }
            //把选中的标签id列表放进dto
            paperRes.setLabIds(labList);
            paperRes.setLabelLists(labelName);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询试卷详情失败！");
        }
        return paperRes;
    }

    /**
     * 新增试卷
     * @return
     */
    @Override
    public void addPaper(AddPaperReq addPaperReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Paper paper = new Paper();
        paper.setId(UuidUtil.getRandomUuid());
        paper.setEid(eId);
        paper.setMemo(addPaperReq.getMemo());
        paper.setName(addPaperReq.getName());
        paper.setTypeId(addPaperReq.getTypeId());
        paper.setTotalScore(addPaperReq.getTotalScore());
        paper.setPassLine(addPaperReq.getPassLine());
        paper.setSpendTime(addPaperReq.getSpendTime());
        paper.setStartStatus(addPaperReq.getStartStatus());
        paper.setReleaseStatus(addPaperReq.getReleaseStatus());
        paper.setIntegral(addPaperReq.getIntegral());
        paper.setSingle(addPaperReq.getSingle());
        paper.setMultiple(addPaperReq.getMultiple());
        paper.setJudge(addPaperReq.getJudge());
        paper.setCreatedTime(new Date());
        paper.setCreateUser(userId);
        //将数据添加到数据表
        mapper.insertSelective(paper);
        //循环保存试卷题目
        for (QuestionList single : addPaperReq.getQuestionLists()) {
            PaperQuestion question = new PaperQuestion();
            question.setEid(eId);
            question.setQuestionId(single.getId());
            question.setPaperId(paper.getId());
            question.setId(UuidUtil.getRandomUuid());
            paperQuestionMapper.insertSelective(question);
        }
    }

    /**
     * 自动生成试卷
     * @return
     */
    @Override
    public void autoCreatePaper(AutoCreatePaperReq autoCreatePaperReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Paper paper = new Paper();
        paper.setId(UuidUtil.getRandomUuid());
        paper.setEid(eId);
        paper.setName(autoCreatePaperReq.getName());
        paper.setMemo(autoCreatePaperReq.getMemo());
        paper.setTypeId(autoCreatePaperReq.getTypeId());
        paper.setTotalScore(autoCreatePaperReq.getTotalScore());
        paper.setPassLine(autoCreatePaperReq.getPassLine());
        paper.setSpendTime(autoCreatePaperReq.getSpendTime());
        paper.setStartStatus(autoCreatePaperReq.getStartStatus());
        paper.setReleaseStatus(autoCreatePaperReq.getReleaseStatus());
        paper.setIntegral(autoCreatePaperReq.getIntegral());
        paper.setSingle(autoCreatePaperReq.getSingle());
        paper.setMultiple(autoCreatePaperReq.getMultiple());
        paper.setJudge(autoCreatePaperReq.getJudge());
        paper.setCreatedTime(new Date());
        paper.setCreateUser(userId);
        //将数据添加到数据表
        mapper.insertSelective(paper);
        String labelIds = null;
        //获取前端传过来的标签id
        List<AutoLabIds> labIdList = autoCreatePaperReq.getLabIds();
        //将标签列表循环进行拼接，用逗号隔开
        for(int i=0;i<labIdList.size();i++){
            labelIds += labIdList.get(i).getId()+",";
        }
        //根据标签id列表以及题目数量随机筛选出相应数量的单选题
        List<QuestionInfo> singleLists = questionInfoMapper.selectSingleList(labelIds,autoCreatePaperReq.getSingle(),eId,1);
        //根据标签id列表以及题目数量随机筛选出相应数量的多选题
        List<QuestionInfo> multipleList = questionInfoMapper.selectSingleList(labelIds,autoCreatePaperReq.getMultiple(),eId,2);
        //根据标签id列表以及题目数量随机筛选出相应数量的判断题
        List<QuestionInfo> judgeList = questionInfoMapper.selectSingleList(labelIds,autoCreatePaperReq.getJudge(),eId,3);
        //合并list，将随机挑选出来的单选、多选、判断题目保存到一个list中
        singleLists.addAll(multipleList);
        singleLists.addAll(judgeList);
        //循环保存试卷题目到数据表中
        for (QuestionInfo single : singleLists) {
            PaperQuestion question = new PaperQuestion();
            question.setEid(eId);
            question.setPaperId(paper.getId());
            question.setQuestionId(single.getId());
            question.setId(UuidUtil.getRandomUuid());
            paperQuestionMapper.insertSelective(question);
        }
    }

    /**
     * 保存修改试卷
     * @return
     */
    @Override
    public void editPaper(EditPaperReq editCerTypeReq, HttpServletRequest request){
        Paper paper = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据试卷id查询试卷信息
        paper = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(paper != null){
            paper.setId(editCerTypeReq.getId());
            paper.setMemo(editCerTypeReq.getMemo());
            paper.setName(editCerTypeReq.getName());
            paper.setTypeId(editCerTypeReq.getTypeId());
            paper.setTotalScore(editCerTypeReq.getTotalScore());
            paper.setPassLine(editCerTypeReq.getPassLine());
            paper.setSpendTime(editCerTypeReq.getSpendTime());
            paper.setStartStatus(editCerTypeReq.getStartStatus());
            paper.setReleaseStatus(editCerTypeReq.getReleaseStatus());
            paper.setIntegral(editCerTypeReq.getIntegral());
            paper.setSingle(editCerTypeReq.getSingle());
            paper.setMultiple(editCerTypeReq.getMultiple());
            paper.setJudge(editCerTypeReq.getJudge());
            paper.setUpdatedTime(new Date());
            paper.setUpdateUser(userId);
            //更新试卷信息
            mapper.updateByPrimaryKeySelective(paper);
            //删除试卷的旧题目
            paperQuestionMapper.deleteByPaperId(editCerTypeReq.getId());
            //循环保存试卷题目
            for (EditQuestionList edit : editCerTypeReq.getQuestionLists()) {
                PaperQuestion question = new PaperQuestion();
                question.setEid(eId);
                question.setPaperId(paper.getId());
                question.setQuestionId(edit.getId());
                question.setId(UuidUtil.getRandomUuid());
                paperQuestionMapper.insertSelective(question);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改试卷信息失败！");
        }

    }

    /**
     * 自动生成--修改试卷信息
     * @return
     */
    @Override
    public void editAutoPaper(EditAutoPaperReq editAutoPaperReq, HttpServletRequest request){
        Paper paper = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据试卷id查询试卷信息
        paper = mapper.selectByPrimaryKey(editAutoPaperReq.getId());
        if(paper != null){
            paper.setId(editAutoPaperReq.getId());
            paper.setMemo(editAutoPaperReq.getMemo());
            paper.setName(editAutoPaperReq.getName());
            paper.setTypeId(editAutoPaperReq.getTypeId());
            paper.setTotalScore(editAutoPaperReq.getTotalScore());
            paper.setPassLine(editAutoPaperReq.getPassLine());
            paper.setSpendTime(editAutoPaperReq.getSpendTime());
            paper.setStartStatus(editAutoPaperReq.getStartStatus());
            paper.setReleaseStatus(editAutoPaperReq.getReleaseStatus());
            paper.setIntegral(editAutoPaperReq.getIntegral());
            paper.setSingle(editAutoPaperReq.getSingle());
            paper.setMultiple(editAutoPaperReq.getMultiple());
            paper.setJudge(editAutoPaperReq.getJudge());
            paper.setUpdatedTime(new Date());
            paper.setUpdateUser(userId);
            //更新试卷信息
            mapper.updateByPrimaryKeySelective(paper);
            //删除试卷的旧题目
            paperQuestionMapper.deleteByPaperId(editAutoPaperReq.getId());
            String labelIds = null;
            //获取前端传过来的标签id
            List<EditAutoLabIds> labIdList = editAutoPaperReq.getLabIds();
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labIdList.size();i++){
                labelIds += labIdList.get(i).getId()+",";
            }
            //根据标签id列表以及题目数量随机筛选出相应数量的单选题
            List<QuestionInfo> singleLists = questionInfoMapper.selectSingleList(labelIds,editAutoPaperReq.getSingle(),eId,1);
            //根据标签id列表以及题目数量随机筛选出相应数量的多选题
            List<QuestionInfo> multipleList = questionInfoMapper.selectSingleList(labelIds,editAutoPaperReq.getMultiple(),eId,2);
            //根据标签id列表以及题目数量随机筛选出相应数量的判断题
            List<QuestionInfo> judgeList = questionInfoMapper.selectSingleList(labelIds,editAutoPaperReq.getJudge(),eId,3);
            //合并list，将随机挑选出来的单选、多选、判断题目保存到一个list中
            singleLists.addAll(multipleList);
            singleLists.addAll(judgeList);
            //循环保存试卷题目到数据表中
            for (QuestionInfo single : singleLists) {
                PaperQuestion question = new PaperQuestion();
                question.setEid(eId);
                question.setPaperId(paper.getId());
                question.setQuestionId(single.getId());
                question.setId(UuidUtil.getRandomUuid());
                paperQuestionMapper.insertSelective(question);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改试卷信息失败！");
        }

    }

    /**
     * 删除试卷
     * @return
     */
    @Override
    public void deletePaper(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "试卷编号不能为空！");
        }
        //根据类型id查询该试卷是否已关联试卷
        int size = examOnlineMapper.selectCountExamOnline(id);
        if(size>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "已有用户考了该张试卷，不能删除！");
        }else {
            //根据id删除试卷
            mapper.deleteByPrimaryKey(id);
        }
    }
    
}
