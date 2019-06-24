package com.zl.school.business.service.impl;

import com.zl.school.business.entity.exam.PaperQuestion;
import com.zl.school.business.dao.exam.PaperQuestionMapper;
import com.zl.school.business.service.IPaperQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class PaperQuestionServiceImpl extends AbstractServiceImpl<PaperQuestion,String> implements IPaperQuestionService {

    @Autowired
    private PaperQuestionMapper mapper;

    public PaperQuestionServiceImpl(PaperQuestionMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
