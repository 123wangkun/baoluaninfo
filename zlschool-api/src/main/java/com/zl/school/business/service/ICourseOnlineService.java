package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.course.*;
import com.zl.school.business.entity.course.CourseOnline;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICourseOnlineService extends IBaseService<CourseOnline,String> {

    /**
     * 查询在线e课程列表
     * @return
     */
    RspPage<GetCourseOnlineListRes> getCourseOnlineList(GetCourseOnlineListReq getCourseOnlineListReq, HttpServletRequest request);

    /**
     * 查询在线e课程详情
     * @return
     */
    GetCourseOnlineRes getCourseOnline(String id, HttpServletRequest request);

    /**
     * 查询审核课程详情
     * @return
     */
    GetProCourseOnlineRes getProCourseOnline(String id, HttpServletRequest request);

    /**
     * 新增在线e课程
     * @return
     */
    void addCourseOnline(AddCourseOnlineReq addCourseOnlineReq, HttpServletRequest request);

    /**
     * 修改在线e课程
     * @return
     */
    void editCourseOnline(EditCourseOnlineReq editCourseOnlineReq, HttpServletRequest request);

    /**
     * 删除在线e课程
     * @return
     */
    void deleteCourseOnline(String id, HttpServletRequest request);



    /**
     * 查询在线活动列表
     * @return
     */
    RspPage<GetOnlineActivityListRes> selectOnlineActivityList(GetOnlineActivityListReq getReq, HttpServletRequest request);

    /**
     * 审核课程
     * @return
     */
    void proCourseOnline(ProCourseOnlineReq proCourseOnlineReq, HttpServletRequest request);
    
}
