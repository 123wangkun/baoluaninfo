package com.zl.school.business.service.impl;

import com.zl.school.business.entity.course.CourseOnlineActivity;
import com.zl.school.business.dao.course.CourseOnlineActivityMapper;
import com.zl.school.business.service.ICourseOnlineActivityService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CourseOnlineActivityServiceImpl extends AbstractServiceImpl<CourseOnlineActivity,String> implements ICourseOnlineActivityService {

    @Autowired
    private CourseOnlineActivityMapper mapper;

    public CourseOnlineActivityServiceImpl(CourseOnlineActivityMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
