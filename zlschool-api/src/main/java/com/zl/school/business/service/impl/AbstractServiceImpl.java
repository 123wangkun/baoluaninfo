package com.zl.school.business.service.impl;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.service.IBaseService;

/**
 * 抽象服务实现类
 *
 * @param <T>
 * @param <PK>
 * @author Xuerong Xue
 */
public class AbstractServiceImpl<T, PK> implements IBaseService<T, PK> {
    private BaseDao<T, PK> base;

    public AbstractServiceImpl(BaseDao<T, PK> base) {
        this.base = base;
    }

    @Override
    public int deleteByPrimaryKey(PK id) {
        return base.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return base.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return base.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(PK id) {
        return base.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return base.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return base.updateByPrimaryKey(record);
    }
}
