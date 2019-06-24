package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.entity.course.CourseUnder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICourseUnderService extends IBaseService<CourseUnder,String> {


    /**
     * 查询线下课程列表
     * @return
     */
    RspPage<GetCourseUnderListRes> getCourseUnderList(GetCourseUnderListReq getCourseUnderListReq, HttpServletRequest request);

    /**
     * 查询线下课程详情
     * @return
     */
    GetCourseUnderRes getCourseUnder(String id, HttpServletRequest request);

    /**
     * 新增线下课程
     * @return
     */
    void addCourseUnder(AddCourseUnderReq addCourseUnderReq, HttpServletRequest request);

    /**
     * 修改线下课程
     * @return
     */
    void editCourseUnder(EditCourseUnderReq editCourseUnderReq, HttpServletRequest request);

    /**
     * 删除线下课程
     * @return
     */
    void deleteCourseUnder(String id, HttpServletRequest request);

    /**
     * 查询已学习用户列表
     * @return
     */
    RspPage<GetCourseUnderUserListRes> selectStudyUserList(GetCourseUnderUserListReq req, HttpServletRequest request);

    /**
     * 审核课程
     * @return
     */
    void proCourseUnder(ProCourseUnderReq proCourseUnderReq, HttpServletRequest request);
}
