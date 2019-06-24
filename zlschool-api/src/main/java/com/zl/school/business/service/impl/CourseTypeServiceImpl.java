package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.course.CourseOnlineMapper;
import com.zl.school.business.dao.course.CourseUnderMapper;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.entity.course.CourseType;
import com.zl.school.business.dao.course.CourseTypeMapper;
import com.zl.school.business.service.ICourseTypeService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CourseTypeServiceImpl extends AbstractServiceImpl<CourseType,String> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper mapper;
    @Autowired
    private CourseUnderMapper courseUnderMapper;
    @Autowired
    private CourseOnlineMapper courseOnlineMapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;

    public CourseTypeServiceImpl(CourseTypeMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询课程类型列表
     * @return
     */
    @Override
    public RspPage<GetCourseTypeListRes> getCourseTypeList(GetCourseTypeListReq getCerTypeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetCourseTypeListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerTypeListReq.getPageNum(), getCerTypeListReq.getPageSize(), true);
        //根据课程类型类型以及企业id查询所有课程类型列表
        List<GetCourseTypeListRes> orders = mapper.selectAllCourseTypeByEid(eId);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询课程详情
     * @return
     */
    @Override
    public GetCourseTypeRes getCourseType(String id, HttpServletRequest request){
        CourseType courseType = null;
        GetCourseTypeRes courseTypeRes = null;
        //根据课程类型id查询课程类型基本信息
        courseType = mapper.selectByPrimaryKey(id);
        if(courseType != null){
            courseTypeRes = new GetCourseTypeRes();
            courseTypeRes.setId(courseType.getId());
            courseTypeRes.setMemo(courseType.getMemo());
            courseTypeRes.setName(courseType.getName());
            courseTypeRes.setPosition(courseType.getPosition());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询课程类型详情失败！");
        }
        return courseTypeRes;
    }

    /**
     * 新增课程类型
     * @return
     */
    @Override
    public void addCourseType(AddCourseTypeReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        CourseType courseType = new CourseType();
        courseType.setId(UuidUtil.getRandomUuid());
        courseType.setEid(eId);
        courseType.setMemo(addCerTypeReq.getMemo());
        courseType.setName(addCerTypeReq.getName());
        courseType.setPosition(addCerTypeReq.getPosition());
        courseType.setCreatedTime(new Date());
        courseType.setCreateUser(userId);
        mapper.insertSelective(courseType);
    }

    /**
     * 修改课程类型
     * @return
     */
    @Override
    public void editCourseType(EditCourseTypeReq editCerTypeReq, HttpServletRequest request){
        CourseType courseType = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据课程类型id查询课程类型信息
        courseType = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(courseType != null){
            courseType.setMemo(editCerTypeReq.getMemo());
            courseType.setName(editCerTypeReq.getName());
            courseType.setPosition(editCerTypeReq.getPosition());
            courseType.setUpdatedTime(new Date());
            courseType.setUpdateUser(userId);
            //更新课程类型信息
            mapper.updateByPrimaryKeySelective(courseType);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改课程类型信息失败！");
        }

    }

    /**
     * 删除课程类型
     * @return
     */
    @Override
    public void deleteCourseType(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "课程类型编号不能为空！");
        }
        //根据类型id查询统计课程表关联该类型的总记录条数
        int courseUnder = courseUnderMapper.isExistByCourseUnder(id);
        int courseOnline = courseOnlineMapper.isExistByCourseOnline(id);
        if(courseUnder > 0 ){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该课程类型已绑定课程，不能删除！");
        }
        if(courseOnline > 0 ){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该课程类型已绑定课程，不能删除！");
        }
        int labelDetail = labelDetailMapper.isExistByLabelDetail(id);
        if(labelDetail > 0){
            //根据课程类型id删除记录
            labelDetailMapper.deleteByRelationId(id);
        }
        //根据id删除课程类型
        mapper.deleteByPrimaryKey(id);
    }
}
