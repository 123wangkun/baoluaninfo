package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.entity.course.CourseType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICourseTypeService extends IBaseService<CourseType,String> {

    /**
     * 查询课程类型列表
     * @return
     */
    RspPage<GetCourseTypeListRes> getCourseTypeList(GetCourseTypeListReq getCourseTypeListReq, HttpServletRequest request);

    /**
     * 查询课程详情
     * @return
     */
    GetCourseTypeRes getCourseType(String id, HttpServletRequest request);

    /**
     * 新增课程类型
     * @return
     */
    void addCourseType(AddCourseTypeReq addCourseTypeReq, HttpServletRequest request);

    /**
     * 修改课程类型
     * @return
     */
    void editCourseType(EditCourseTypeReq editCourseTypeReq, HttpServletRequest request);

    /**
     * 删除课程类型
     * @return
     */
    void deleteCourseType(String id, HttpServletRequest request);
}
