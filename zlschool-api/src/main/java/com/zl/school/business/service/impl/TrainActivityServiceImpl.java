package com.zl.school.business.service.impl;

import com.zl.school.business.dao.train.TrainActivityMapper;
import com.zl.school.business.entity.train.TrainActivity;
import com.zl.school.business.service.ITrainActivityService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class TrainActivityServiceImpl extends AbstractServiceImpl<TrainActivity,String> implements ITrainActivityService {

    @Autowired
    private TrainActivityMapper mapper;

    public TrainActivityServiceImpl(TrainActivityMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
