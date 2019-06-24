package com.zl.school.business.service.impl;

import com.zl.school.business.entity.exam.ExamUnderDetail;
import com.zl.school.business.dao.exam.ExamUnderDetailMapper;
import com.zl.school.business.service.IExamUnderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class ExamUnderDetailServiceImpl extends AbstractServiceImpl<ExamUnderDetail,String> implements IExamUnderDetailService {

    @Autowired
    private ExamUnderDetailMapper mapper;

    public ExamUnderDetailServiceImpl(ExamUnderDetailMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
