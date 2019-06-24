package com.zl.school.business.service.impl;

import com.zl.school.business.entity.exam.QuestionOptions;
import com.zl.school.business.dao.exam.QuestionOptionsMapper;
import com.zl.school.business.service.IQuestionOptionsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class QuestionOptionsServiceImpl extends AbstractServiceImpl<QuestionOptions,String> implements IQuestionOptionsService {

    @Autowired
    private QuestionOptionsMapper mapper;

    public QuestionOptionsServiceImpl(QuestionOptionsMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
