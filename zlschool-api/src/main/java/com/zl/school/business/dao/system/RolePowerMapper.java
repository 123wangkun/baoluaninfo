package com.zl.school.business.dao.system;

import com.zl.school.business.entity.system.RolePower;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author MrBird
 */
public interface RolePowerMapper extends BaseDao<RolePower,String> {

    /**
     * 统计该企业下的该角色总共有多少个菜单
     * @return
     */
    Integer selectCount(String eid,String roleId);

    /**
     * 根据企业id  角色id删除记录
     * @return
     */
    void deleteRoles(String eid,String roleId);

    /**
     * 根据企业id和角色id查询菜单列表
     * @return
     */
    List<RolePower> selectByKey(RolePower rolePower);

}
