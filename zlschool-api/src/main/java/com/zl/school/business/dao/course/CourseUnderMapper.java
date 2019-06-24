package com.zl.school.business.dao.course;

import com.zl.school.business.dto.course.GetCourseUnderListReq;
import com.zl.school.business.dto.course.GetCourseUnderListRes;
import com.zl.school.business.dto.course.GetCourseUnderUserListReq;
import com.zl.school.business.dto.course.GetCourseUnderUserListRes;
import com.zl.school.business.entity.course.CourseUnder;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CourseUnderMapper extends BaseDao<CourseUnder,String> {

    /**
     * 统计关联某类型的线下课程的总数量
     * @return
     */
    int isExistByCourseUnder(String typeId);
    /**
     * 根据企业id查询所有线下课程
     * @return
     */
    List<GetCourseUnderListRes> selectAllCourseUnderByEid(GetCourseUnderListReq getCerTypeListReq);

    /**
     * 查询已学习用户列表
     * @return
     */
    List<GetCourseUnderUserListRes> selectStudyUserList(GetCourseUnderUserListReq req);
}
