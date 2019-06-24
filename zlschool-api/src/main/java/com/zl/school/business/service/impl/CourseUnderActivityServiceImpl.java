package com.zl.school.business.service.impl;

import com.zl.school.business.dao.course.CourseUnderActivityMapper;
import com.zl.school.business.entity.course.CourseUnderActivity;
import com.zl.school.business.service.ICourseUnderActivityService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CourseUnderActivityServiceImpl extends AbstractServiceImpl<CourseUnderActivity,String> implements ICourseUnderActivityService {

    @Autowired
    private CourseUnderActivityMapper mapper;

    public CourseUnderActivityServiceImpl(CourseUnderActivityMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
