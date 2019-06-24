package com.zl.school.business.dao.course;

import com.zl.school.business.entity.course.CourseUnderActivity;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CourseUnderActivityMapper extends BaseDao<CourseUnderActivity,String> {
    /**
     * 统计关联某线下课程的总数量
     * @return
     */
    List<CourseUnderActivity> selectByCourseId(String courseId);
}
