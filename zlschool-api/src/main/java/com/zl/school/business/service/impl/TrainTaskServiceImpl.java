package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.course.CourseOnlineMapper;
import com.zl.school.business.dao.course.CourseUnderMapper;
import com.zl.school.business.dao.exam.ExamUnderMapper;
import com.zl.school.business.dao.exam.PaperMapper;
import com.zl.school.business.dao.train.TrainActivityMapper;
import com.zl.school.business.dto.train.AddTrainTaskReq;
import com.zl.school.business.dto.train.EditTrainTaskReq;
import com.zl.school.business.dto.train.GetTrainTaskListRes;
import com.zl.school.business.dto.train.GetTrainTaskRes;
import com.zl.school.business.entity.course.CourseOnline;
import com.zl.school.business.entity.course.CourseUnder;
import com.zl.school.business.entity.exam.ExamUnder;
import com.zl.school.business.entity.exam.Paper;
import com.zl.school.business.entity.train.TrainTask;
import com.zl.school.business.dao.train.TrainTaskMapper;
import com.zl.school.business.service.ITrainTaskService;
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
public class TrainTaskServiceImpl extends AbstractServiceImpl<TrainTask,String> implements ITrainTaskService {

    @Autowired
    private TrainTaskMapper mapper;
    @Autowired
    private CourseOnlineMapper onlineMapper;
    @Autowired
    private CourseUnderMapper underMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private ExamUnderMapper examUnderMapper;
    @Autowired
    private TrainActivityMapper trainActivityMapper;

    public TrainTaskServiceImpl(TrainTaskMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询培训任务列表
     * @return
     */
    @Override
    public List<GetTrainTaskListRes> getTrainTaskList( String trainId){
        //根据培训任务类型以及企业id查询所有培训任务列表
        List<GetTrainTaskListRes> orders = mapper.selectAllTaskByTrainId(trainId);
        return orders;
    }

    /**
     * 查询培训任务详情
     * @return
     */
    @Override
    public GetTrainTaskRes getTrainTask(String id, HttpServletRequest request){
        TrainTask trainTask = null;
        CourseOnline courseOnline=null;
        CourseUnder courseUnder=null;
        Paper paper = null;
        ExamUnder examUnder = null;
        String relationName = "";
        GetTrainTaskRes trainTaskRes = null;
        //根据培训任务id查询培训任务基本信息
        trainTask = mapper.selectByPrimaryKey(id);
        if(trainTask != null){
            trainTaskRes = new GetTrainTaskRes();
            trainTaskRes.setName(trainTask.getName());
            trainTaskRes.setEndTime(trainTask.getEndTime());
            trainTaskRes.setPosition(trainTask.getPosition());
            trainTaskRes.setMemo(trainTask.getMemo());
            trainTaskRes.setType(trainTask.getType());
            trainTaskRes.setRelationId(trainTask.getRelationId());
            //根据不同的类型，去查询不同的数据表，并得到对应类型的名称
            switch(trainTask.getType()){
                case 1:
                    trainTaskRes.setTypeName("线上课程");
                    courseOnline = onlineMapper.selectByPrimaryKey(trainTask.getRelationId());
                    relationName = courseOnline.getName();
                    break;
                case 2:
                    trainTaskRes.setTypeName("线下课程");
                    courseUnder = underMapper.selectByPrimaryKey(trainTask.getRelationId());
                    relationName = courseUnder.getName();
                    break;
                case 3:
                    trainTaskRes.setTypeName("线上考试");
                    paper = paperMapper.selectByPrimaryKey(trainTask.getRelationId());
                    relationName = paper.getName();
                    break;
                case 4:
                    trainTaskRes.setTypeName("线下考试");
                    examUnder = examUnderMapper.selectByPrimaryKey(trainTask.getRelationId());
                    relationName = examUnder.getName();
                    break;
            }
            trainTaskRes.setRelationName(relationName);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询培训任务详情失败！");
        }
        return trainTaskRes;
    }

    /**
     * 新增培训任务
     * @return
     */
    @Override
    public void addTrainTask(AddTrainTaskReq addTrainTaskReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        TrainTask trainTask = new TrainTask();
        trainTask.setId(UuidUtil.getRandomUuid());
        trainTask.setEid(eId);
        trainTask.setName(addTrainTaskReq.getName());
        trainTask.setEndTime(addTrainTaskReq.getEndTime());
        trainTask.setPosition(addTrainTaskReq.getPosition());
        trainTask.setMemo(addTrainTaskReq.getMemo());
        trainTask.setType(addTrainTaskReq.getType());
        trainTask.setRelationId(addTrainTaskReq.getRelationId());
        //添加数据到数据库
        mapper.insertSelective(trainTask);
    }

    /**
     * 修改培训任务
     * @return
     */
    @Override
    public void editTrainTask(EditTrainTaskReq editTrainTaskReq, HttpServletRequest request){
        TrainTask trainTask = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据培训任务id查询培训任务信息
        trainTask = mapper.selectByPrimaryKey(editTrainTaskReq.getId());
        if(trainTask != null){
            trainTask.setName(editTrainTaskReq.getName());
            trainTask.setEndTime(editTrainTaskReq.getEndTime());
            trainTask.setPosition(editTrainTaskReq.getPosition());
            trainTask.setMemo(editTrainTaskReq.getMemo());
            trainTask.setType(editTrainTaskReq.getType());
            trainTask.setRelationId(editTrainTaskReq.getRelationId());
            //更新培训任务信息
            mapper.updateByPrimaryKeySelective(trainTask);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改培训任务信息失败！");
        }

    }

    /**
     * 删除培训任务
     * @return
     */
    @Override
    public void deleteTrainTask(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "培训任务编号不能为空！");
        }
        try {
            //根据类型id查询该培训任务是否已关联用户
            int size = trainActivityMapper.selectCountTask(id);
            if (size > 0) {
                throw new BusinessException(ErrorCode.ERROR_PHONE, "已有用户参与了该任务，不能删除！");
            } else {
                //根据id删除培训任务
                mapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }
}
