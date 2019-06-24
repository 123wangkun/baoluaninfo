package com.zl.school.business.service.impl;

import com.zl.school.business.entity.system.RolePower;
import com.zl.school.business.dao.system.RolePowerMapper;
import com.zl.school.business.service.IRolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MrBird
 */
@Service
public class RolePowerServiceImpl extends AbstractServiceImpl<RolePower,String> implements IRolePowerService {

    @Autowired
    private RolePowerMapper mapper;

    public RolePowerServiceImpl(RolePowerMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
