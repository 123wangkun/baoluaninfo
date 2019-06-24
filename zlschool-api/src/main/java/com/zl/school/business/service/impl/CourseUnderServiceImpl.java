package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.course.CourseTypeMapper;
import com.zl.school.business.dao.course.CourseUnderActivityMapper;
import com.zl.school.business.dao.course.CourseUnderMapper;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dto.course.AddCourseUnderReq.LabelList;
import com.zl.school.business.dto.course.EditCourseUnderReq.EditLabelList;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.entity.course.CourseType;
import com.zl.school.business.entity.course.CourseUnder;
import com.zl.school.business.entity.course.CourseUnderActivity;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.service.ICourseUnderService;
import com.zl.school.business.util.UuidUtil;
import com.zl.school.business.dto.course.GetCourseUnderRes.UnderLabIds;
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
public class CourseUnderServiceImpl extends AbstractServiceImpl<CourseUnder,String> implements ICourseUnderService {

    @Autowired
    private CourseUnderMapper mapper;
    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private LabelMapper labMapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    @Autowired
    private CourseUnderActivityMapper courseUnderActivityMapper;
    public CourseUnderServiceImpl(CourseUnderMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询线下课程列表
     * @return
     */
    @Override
    public RspPage<GetCourseUnderListRes> getCourseUnderList(GetCourseUnderListReq getCerTypeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetCourseUnderListRes> rspPage = new RspPage<>();
        getCerTypeListReq.setEid(eId);
        //初始化分页
        Page page = PageHelper.startPage(getCerTypeListReq.getPageNum(), getCerTypeListReq.getPageSize(), true);
        //根据线下课程类型以及企业id查询所有线下课程列表
        List<GetCourseUnderListRes> orders = mapper.selectAllCourseUnderByEid(getCerTypeListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询线下课程详情
     * @return
     */
    @Override
    public GetCourseUnderRes getCourseUnder(String id, HttpServletRequest request){
        CourseUnder courseUnder = null;
        GetCourseUnderRes courseUnderRes = null;
        //根据线下课程id查询线下课程基本信息
        courseUnder = mapper.selectByPrimaryKey(id);
        if(courseUnder != null){
            courseUnderRes = new GetCourseUnderRes();
            CourseType courseType = courseTypeMapper.selectByPrimaryKey(courseUnder.getTypeId());
            courseUnderRes.setId(courseUnder.getId());
            courseUnderRes.setName(courseUnder.getName());
            courseUnderRes.setDescript(courseUnder.getDescript());
            courseUnderRes.setAddress(courseUnder.getAddress());
            courseUnderRes.setState(courseUnder.getState());
            courseUnderRes.setMemo(courseUnder.getMemo());
            courseUnderRes.setStudyTime(courseUnder.getStudyTime());
            courseUnderRes.setTotalTime(courseUnder.getTotalTime());
            courseUnderRes.setPosition(courseUnder.getPosition());
            courseUnderRes.setTypeName(courseType.getName());
            //1审核中;2进行中;3已结束;4未启用;
            switch(courseUnder.getState()){
                case 1:
                    courseUnderRes.setStateName("已发布");
                    break;
                case 2:
                    courseUnderRes.setStateName("未发布");
                    break;
            }
            String labelName = null;
            List<UnderLabIds> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(courseUnder.getId());
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                UnderLabIds lab = new UnderLabIds();
                lab.setId(labNameList.get(i).getId());
                labList.add(lab);
            }
            courseUnderRes.setLabIds(labList);
            courseUnderRes.setLabelLists(labelName);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询线下课程详情失败！");
        }
        return courseUnderRes;
    }

    /**
     * 新增线下课程
     * @return
     */
    @Override
    public void addCourseUnder(AddCourseUnderReq addCourseUnderReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        CourseUnder courseUnder = new CourseUnder();
        courseUnder.setId(UuidUtil.getRandomUuid());
        courseUnder.setEid(eId);
        courseUnder.setName(addCourseUnderReq.getName());
        courseUnder.setDescript(addCourseUnderReq.getDescript());
        courseUnder.setAddress(addCourseUnderReq.getAddress());
        courseUnder.setState(addCourseUnderReq.getState());
        courseUnder.setProStatus(1);
        courseUnder.setMemo(addCourseUnderReq.getMemo());
        courseUnder.setStudyTime(addCourseUnderReq.getStudyTime());
        courseUnder.setTotalTime(addCourseUnderReq.getTotalTime());
        courseUnder.setPosition(addCourseUnderReq.getPosition());
        courseUnder.setCreatedTime(new Date());
        courseUnder.setCreateUser(userId);
        //把课程添加到线下课程表
        mapper.insertSelective(courseUnder);
        //获取前端传过来的标签列表
        List<LabelList> labelIds = addCourseUnderReq.getLabelIds();
        //循环保存关联信息到课程标签详情表
        for(LabelList lab : labelIds){
            LabelDetail labelDetail = new LabelDetail();
            labelDetail.setEid(eId);
            labelDetail.setId(UuidUtil.getRandomUuid());
            labelDetail.setLableId(lab.getLabelId());
            labelDetail.setRelationId(courseUnder.getId());
            labelDetail.setType(LabelDetail.course_type);
            labelDetail.setCreatedTime(new Date());
            labelDetail.setCreateUser(userId);
            //添加课程标签详情记录
            labelDetailMapper.insertSelective(labelDetail);
        }
    }

    /**
     * 修改线下课程
     * @return
     */
    @Override
    public void editCourseUnder(EditCourseUnderReq editCerTypeReq, HttpServletRequest request){
        CourseUnder courseUnder = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        //根据线下课程id查询线下课程信息
        courseUnder = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(courseUnder != null){
            courseUnder.setName(editCerTypeReq.getName());
            courseUnder.setDescript(editCerTypeReq.getDescript());
            courseUnder.setAddress(editCerTypeReq.getAddress());
            courseUnder.setState(editCerTypeReq.getState());
            courseUnder.setMemo(editCerTypeReq.getMemo());
            courseUnder.setStudyTime(editCerTypeReq.getStudyTime());
            courseUnder.setTotalTime(editCerTypeReq.getTotalTime());
            courseUnder.setPosition(editCerTypeReq.getPosition());
            courseUnder.setUpdatedTime(new Date());
            courseUnder.setUpdateUser(userId);
            //更新线下课程信息
            mapper.updateByPrimaryKeySelective(courseUnder);
            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(editCerTypeReq.getId());
            //根据课程id循环删除该课程关联的标签记录
            if(size>0){
                labelDetailMapper.deleteByRelationId(editCerTypeReq.getId());
            }
            //获取前端传过来的标签列表
            List<EditLabelList> labelIds = editCerTypeReq.getEditLabelLists();
            //循环保存关联信息到课程标签详情表
            for(EditLabelList label : labelIds){
                LabelDetail labDetail = new LabelDetail();
                labDetail.setEid(eId);
                labDetail.setId(UuidUtil.getRandomUuid());
                labDetail.setLableId(label.getLabelId());
                labDetail.setRelationId(courseUnder.getId());
                labDetail.setType(LabelDetail.course_type);
                labDetail.setCreatedTime(new Date());
                labDetail.setCreateUser(userId);
                //添加课程标签详情记录
                labelDetailMapper.insertSelective(labDetail);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改线下课程信息失败！");
        }

    }

    /**
     * 删除线下课程
     * @return
     */
    @Override
    public void deleteCourseUnder(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "线下课程编号不能为空！");
        }
        //查询该课程是否已有人参加，如果有，则不能删除
        List<CourseUnderActivity>  activityList = courseUnderActivityMapper.selectByCourseId(id);
        if(activityList.size()>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该课程已有用户参加不能删除！");
        }else {
            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(id);
            if(size>0){
                labelDetailMapper.deleteByRelationId(id);
            }
            //根据id删除线下课程
            mapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 查询线下课程已学习用户列表
     * @return
     */
    @Override
    public RspPage<GetCourseUnderUserListRes> selectStudyUserList(GetCourseUnderUserListReq getCourseUnderUserListReq, HttpServletRequest request){
        RspPage<GetCourseUnderUserListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCourseUnderUserListReq.getPageNum(), getCourseUnderUserListReq.getPageSize(), true);
        //根据企业id查询学习了该课程的所有用户
        List<GetCourseUnderUserListRes> orders = mapper.selectStudyUserList(getCourseUnderUserListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 审核课程
     * @return
     */
    @Override
    public void proCourseUnder(ProCourseUnderReq proCourseUnderReq, HttpServletRequest request){
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        CourseUnder courseUnder = null;
        //根据线下课程id查询线下课程信息
        courseUnder = mapper.selectByPrimaryKey(proCourseUnderReq.getId());
        if(courseUnder != null){
            //把审核信息保存到数据表
            courseUnder.setProContent(proCourseUnderReq.getProContent());
            courseUnder.setProdTime(new Date());
            courseUnder.setProMemo(proCourseUnderReq.getProMemo());
            courseUnder.setProUser(userId);
            courseUnder.setProStatus(proCourseUnderReq.getProStatus());
            courseUnder.setUpdatedTime(new Date());
            courseUnder.setUpdateUser(userId);
            //更新线下课程表记录
            mapper.updateByPrimaryKeySelective(courseUnder);
        }
    }
}
