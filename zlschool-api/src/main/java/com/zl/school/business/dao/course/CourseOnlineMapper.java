package com.zl.school.business.dao.course;

import com.zl.school.business.dto.course.GetCourseOnlineListReq;
import com.zl.school.business.dto.course.GetCourseOnlineListRes;
import com.zl.school.business.dto.course.GetOnlineActivityListReq;
import com.zl.school.business.dto.course.GetOnlineActivityListRes;
import com.zl.school.business.entity.course.CourseOnline;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CourseOnlineMapper extends BaseDao<CourseOnline,String> {
    /**
     * 统计关联某类型的线上课程的总数量
     * @return
     */
    int isExistByCourseOnline(String typeId);

    /**
     * 根据企业id查询在线课程列表
     * @return
     */
    List<GetCourseOnlineListRes> selectAllCourseOnlineByEid(GetCourseOnlineListReq getCourseOnlineListReq);

    /**
     * 查询在线课程活动列表
     * @return
     */
    List<GetOnlineActivityListRes> selectOnlineActivityList(GetOnlineActivityListReq req);
}
