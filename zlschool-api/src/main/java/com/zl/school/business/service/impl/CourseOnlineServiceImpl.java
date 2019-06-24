package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.course.CourseOnlineActivityMapper;
import com.zl.school.business.dao.course.CourseOnlineMapper;
import com.zl.school.business.dao.course.CourseTypeMapper;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.dto.course.AddCourseOnlineReq.OnlineLabelList;
import com.zl.school.business.entity.course.CourseOnline;
import com.zl.school.business.entity.course.CourseOnlineActivity;
import com.zl.school.business.entity.course.CourseType;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.service.ICourseOnlineService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.school.business.dto.course.GetCourseOnlineRes.OnlineLabIds;
import com.zl.school.business.dto.course.EditCourseOnlineReq.OnlineEditLabelList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CourseOnlineServiceImpl extends AbstractServiceImpl<CourseOnline,String> implements ICourseOnlineService {

    @Autowired
    private CourseOnlineMapper mapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    @Autowired
    private CourseOnlineActivityMapper courseOnlineActivityMapper;
    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private LabelMapper labMapper;

    public CourseOnlineServiceImpl(CourseOnlineMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询在线课程列表
     * @return
     */
    @Override
    public RspPage<GetCourseOnlineListRes> getCourseOnlineList(GetCourseOnlineListReq getCourseOnlineListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetCourseOnlineListRes> rspPage = new RspPage<>();
        getCourseOnlineListReq.setEId(eId);
        //初始化分页
        Page page = PageHelper.startPage(getCourseOnlineListReq.getPageNum(), getCourseOnlineListReq.getPageSize(), true);
        //搜索查询的判断
        if(getCourseOnlineListReq.getState() == 1 || getCourseOnlineListReq.getState() == 2  ){
            //如果是选了待审核或者审核不通过
            switch(getCourseOnlineListReq.getState()) {
                case 1:
                    getCourseOnlineListReq.setProStatus(1);
                    break;
                case 2:
                    getCourseOnlineListReq.setProStatus(2);
                    break;
            }
            getCourseOnlineListReq.setState(-1);
        }else if(getCourseOnlineListReq.getState() == 3 || getCourseOnlineListReq.getState() == 4 ){
            getCourseOnlineListReq.setProStatus(-1);
            //判断状态类型，如果为3，则代表为启用，为4则代表为禁用
            switch(getCourseOnlineListReq.getState()) {
                case 3:
                    getCourseOnlineListReq.setState(1);
                    break;
                case 4:
                    getCourseOnlineListReq.setState(2);
                    break;
            }
        }
        //根据企业id查询在线课程列表
        List<GetCourseOnlineListRes> orders = mapper.selectAllCourseOnlineByEid(getCourseOnlineListReq);
        List<GetCourseOnlineListRes> onlineListRes = new ArrayList<>();
        for(GetCourseOnlineListRes lab : orders){
            GetCourseOnlineListRes online = new GetCourseOnlineListRes();
            online = lab;
            //根据课程id查询统计课程的学习数
            int studyNumber = courseOnlineActivityMapper.selectStudyNumber(1,online.getId());
            online.setStudyNumber(studyNumber);
            //根据课程id查询统计课程的点赞数
            int likeNumber = courseOnlineActivityMapper.selectStudyNumber(3,online.getId());
            online.setLikeNumber(likeNumber);
            //根据课程id查询统计课程的收藏数
            int collectionNumber = courseOnlineActivityMapper.selectStudyNumber(4,online.getId());
            online.setCollectionNumber(collectionNumber);
            //添加课程标签详情记录
            onlineListRes.add(online);
        }
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 新增在线e课程
     * @return
     */
    @Override
    public void addCourseOnline(AddCourseOnlineReq addCourseOnlineReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        CourseOnline courseOnline = new CourseOnline();
        courseOnline.setId(UuidUtil.getRandomUuid());
        courseOnline.setEid(eId);
        courseOnline.setName(addCourseOnlineReq.getName());
        courseOnline.setDescript(addCourseOnlineReq.getDescript());
        courseOnline.setFileSrc(addCourseOnlineReq.getFileSrc());
        courseOnline.setFileSize(addCourseOnlineReq.getFileSize());
        courseOnline.setFileType(addCourseOnlineReq.getFileType());
        courseOnline.setLogoSrc(addCourseOnlineReq.getLogoSrc());
        courseOnline.setMobileSrc(addCourseOnlineReq.getMobileSrc());
        courseOnline.setState(addCourseOnlineReq.getState());
        courseOnline.setMemo(addCourseOnlineReq.getMemo());
        courseOnline.setTotalTime(addCourseOnlineReq.getTotalTime());
        courseOnline.setTypeId(addCourseOnlineReq.getTypeId());
        courseOnline.setPosition(addCourseOnlineReq.getPosition());
        courseOnline.setCreatedTime(new Date());
        courseOnline.setCreateUser(userId);
        //把课程添加到在线课程表
        mapper.insertSelective(courseOnline);
        //获取前端传过来的标签列表
        List<OnlineLabelList> labelIds = addCourseOnlineReq.getLabelIds();
        //循环保存关联信息到课程标签详情表
        for(OnlineLabelList lab : labelIds){
            LabelDetail labelDetail = new LabelDetail();
            labelDetail.setEid(eId);
            labelDetail.setId(UuidUtil.getRandomUuid());
            labelDetail.setLableId(lab.getLabelId());
            labelDetail.setRelationId(courseOnline.getId());
            labelDetail.setType(LabelDetail.course_type);
            labelDetail.setCreatedTime(new Date());
            labelDetail.setCreateUser(userId);
            //添加课程标签详情记录
            labelDetailMapper.insertSelective(labelDetail);
        }
    }

    /**
     * 修改在线e课程
     * @return
     */
    @Override
    public void editCourseOnline(EditCourseOnlineReq editCourseOnlineReq, HttpServletRequest request){
        CourseOnline courseOnline = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        //根据线下课程id查询线下课程信息
        courseOnline = mapper.selectByPrimaryKey(editCourseOnlineReq.getId());
        if(courseOnline != null){
            courseOnline.setName(editCourseOnlineReq.getName());
            courseOnline.setDescript(editCourseOnlineReq.getDescript());
            courseOnline.setFileSrc(editCourseOnlineReq.getFileSrc());
            courseOnline.setFileSize(editCourseOnlineReq.getFileSize());
            courseOnline.setFileType(editCourseOnlineReq.getFileType());
            courseOnline.setLogoSrc(editCourseOnlineReq.getLogoSrc());
            courseOnline.setMobileSrc(editCourseOnlineReq.getMobileSrc());
            courseOnline.setState(editCourseOnlineReq.getState());
            courseOnline.setMemo(editCourseOnlineReq.getMemo());
            courseOnline.setTotalTime(editCourseOnlineReq.getTotalTime());
            courseOnline.setPosition(editCourseOnlineReq.getPosition());
            courseOnline.setUpdatedTime(new Date());
            courseOnline.setUpdateUser(userId);
            //更新线下课程信息
            mapper.updateByPrimaryKeySelective(courseOnline);
            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(courseOnline.getId());
            //根据课程id删除该课程关联的标签记录
            if(size>0){
                labelDetailMapper.deleteByRelationId(courseOnline.getId());
            }
            //获取前端传过来的标签列表
            List<OnlineEditLabelList> labelIds = editCourseOnlineReq.getLabelIds();
            //循环保存关联信息到课程标签详情表
            for(OnlineEditLabelList label : labelIds){
                LabelDetail labDetail = new LabelDetail();
                labDetail.setEid(eId);
                labDetail.setId(UuidUtil.getRandomUuid());
                labDetail.setLableId(label.getLabelId());
                labDetail.setRelationId(courseOnline.getId());
                labDetail.setType(LabelDetail.course_type);
                labDetail.setCreatedTime(new Date());
                labDetail.setCreateUser(userId);
                //添加课程标签详情记录
                labelDetailMapper.insertSelective(labDetail);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改在线课程信息失败！");
        }
    }


    /**
     * 查询在线e课程详情
     * @return
     */
    @Override
    public GetCourseOnlineRes getCourseOnline(String id, HttpServletRequest request){
        CourseOnline courseOnline = null;
        GetCourseOnlineRes courseOnlineRes = null;
        //根据线下课程id查询线下课程基本信息
        courseOnline = mapper.selectByPrimaryKey(id);
        if(courseOnline != null){
            courseOnlineRes = new GetCourseOnlineRes();
            CourseType courseType = courseTypeMapper.selectByPrimaryKey(courseOnline.getTypeId());
            courseOnlineRes.setId(courseOnline.getId());
            courseOnlineRes.setName(courseOnline.getName());
            courseOnlineRes.setDescript(courseOnline.getDescript());
            courseOnlineRes.setFileSrc(courseOnline.getFileSrc());
            courseOnlineRes.setFileSize(courseOnline.getFileSize());
            courseOnlineRes.setFileType(courseOnline.getFileType());
            courseOnlineRes.setLogoSrc(courseOnline.getLogoSrc());
            courseOnlineRes.setMobileSrc(courseOnline.getMobileSrc());
            courseOnlineRes.setState(courseOnline.getState());
            courseOnlineRes.setMemo(courseOnline.getMemo());
            courseOnlineRes.setTotalTime(courseOnline.getTotalTime());
            courseOnlineRes.setPosition(courseOnline.getPosition());
            courseOnlineRes.setTypeName(courseType.getName());
            //1审核中;2进行中;3已结束;4未启用;
            switch(courseOnlineRes.getState()){
                case 1:
                    courseOnlineRes.setStateName("已发布");
                    break;
                case 2:
                    courseOnlineRes.setStateName("未发布");
                    break;
            }
            String labelName = "";
            List<OnlineLabIds> labList = new ArrayList<>();
            //根据课程id查询该课程所拥有的标签
            List<Label> labNameList = labMapper.selectLabelById(id);
            //将标签列表循环进行拼接，用逗号隔开
            for(int i=0;i<labNameList.size();i++){
                labelName += labNameList.get(i).getName()+"，";
                OnlineLabIds lab = new OnlineLabIds();
                lab.setId(labNameList.get(i).getId());
                labList.add(lab);
            }
            courseOnlineRes.setLabIds(labList);
            courseOnlineRes.setLabelLists(labelName);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询在线课程详情失败！");
        }
        return courseOnlineRes;
    }

    /**
     * 查询审核课程详情
     * @return
     */
    @Override
    public GetProCourseOnlineRes getProCourseOnline(String id, HttpServletRequest request){
        CourseOnline courseOnline = null;
        GetProCourseOnlineRes proCourseOnlineRes = null;
        //根据线下课程id查询线下课程基本信息
        courseOnline = mapper.selectByPrimaryKey(id);
        if(courseOnline != null) {
            proCourseOnlineRes = new GetProCourseOnlineRes();
            proCourseOnlineRes.setId(courseOnline.getId());
            proCourseOnlineRes.setProStatus(courseOnline.getProStatus());
            proCourseOnlineRes.setProContent(courseOnline.getProContent());
            proCourseOnlineRes.setProMemo(courseOnline.getProMemo());
        }
        return proCourseOnlineRes;
    }

    /**
     * 删除在线e课程
     * @return
     */
    @Override
    public void deleteCourseOnline(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "线下课程编号不能为空！");
        }
        //查询该课程是否活动关联，如果有，则不能删除
        List<CourseOnlineActivity>  activityList = courseOnlineActivityMapper.selectOnlineByCourseId(id);
        if(activityList.size()>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该课程已有用户参加不能删除！");
        }else {
            //查询统计该课程下的共关联多少标签
            int size = labelDetailMapper.isExistByLabelDetail(id);
            if(size>0){
                //根据课程id循环删除该课程关联的标签记录
                for(int i=0;i<size;i++){
                    labelDetailMapper.deleteByRelationId(id);
                }
            }
            //根据id删除在线课程
            mapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 查询在线活动列表
     * @return
     */
    @Override
    public RspPage<GetOnlineActivityListRes> selectOnlineActivityList(GetOnlineActivityListReq getReq, HttpServletRequest request){
        RspPage<GetOnlineActivityListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getReq.getPageNum(), getReq.getPageSize(), true);
        //根据企业id查询学习了该课程的所有用户
        List<GetOnlineActivityListRes> orders = mapper.selectOnlineActivityList(getReq);
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
    public void proCourseOnline(ProCourseOnlineReq proReq, HttpServletRequest request){
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        CourseOnline courseOnline = null;
        //根据线下课程id查询线下课程信息
        courseOnline = mapper.selectByPrimaryKey(proReq.getId());
        if(courseOnline != null){
            //把审核信息保存到数据表
            courseOnline.setProContent(proReq.getProContent());
            courseOnline.setProMemo(proReq.getProMemo());
            courseOnline.setProUser(userId);
            courseOnline.setProStatus(proReq.getProStatus());
            courseOnline.setProdTime(new Date());
            courseOnline.setUpdatedTime(new Date());
            courseOnline.setUpdateUser(userId);
            //更新线下课程表记录
            mapper.updateByPrimaryKeySelective(courseOnline);
        }
    }
}
