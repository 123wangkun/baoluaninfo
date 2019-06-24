package com.zl.school.business.dao.course;

import com.zl.school.business.entity.course.CourseOnlineActivity;
import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.course.CourseUnderActivity;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CourseOnlineActivityMapper extends BaseDao<CourseOnlineActivity,String> {
    /**
     * 统计关联某在线课程的总数量
     * @return
     */
    List<CourseOnlineActivity> selectOnlineByCourseId(String courseId);
    /**
     * 统计在线课程不同活动类型的总数量
     * @return
     */
    int selectStudyNumber(int type,String onlineId);
}
