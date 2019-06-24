package com.zl.school.business.dao;

/**
 * <p>Title: BaseDAO</p>
 * <p>Description: 基本DAO接口</p>
 *
 * @param <T>  记录数据类型
 * @param <PK> 记录主键类型
 * @author zhangsl
 */
public interface BaseDao<T, PK> {
    /**
     * 根据主键删除
     *
     * @param id 记录主键
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 插入数据
     *
     * @param record 记录
     * @return
     */
    int insert(T record);

    /**
     * 选择性插入数据
     *
     * @param record 记录
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据主键查询
     *
     * @param id 记录主键
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 根据主键选择性更新数据
     *
     * @param record 记录
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新数据
     *
     * @param record 记录
     * @return
     */
    int updateByPrimaryKey(T record);
}
