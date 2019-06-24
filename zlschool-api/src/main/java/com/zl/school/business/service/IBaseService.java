package com.zl.school.business.service;

/**
 * <p>Title: IBaseService</p>
 * <p>Description: 基本服务接口</p>
 *
 * @author zhangsl
 */
public interface IBaseService<T, PK> {

    /**
     * 根据主键删除
     *
     * @param id 记录主键值
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
     * @param id 记录主键值
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 更新非空字段的数据
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
