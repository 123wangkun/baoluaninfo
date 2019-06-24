package com.zl.school.business.service.impl;

import com.zl.school.business.entity.system.Menu;
import com.zl.school.business.dao.system.MenuMapper;
import com.zl.school.business.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu,String> implements IMenuService {

    @Autowired
    private MenuMapper mapper;

    public MenuServiceImpl(MenuMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
