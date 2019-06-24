package com.zl.school.business.dao.course;

import com.zl.school.business.dto.course.GetCourseTypeListRes;
import com.zl.school.business.entity.course.CourseType;
import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.label.Label;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CourseTypeMapper extends BaseDao<CourseType,String> {
    /**
     * 查询企业下的所有标签
     * @return
     */
    List<GetCourseTypeListRes> selectAllCourseTypeByEid(String eid);
}
