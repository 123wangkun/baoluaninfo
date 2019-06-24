package com.zl.school.business.dao.system;

import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.Role;
import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.system.RolePower;

import java.util.List;

/**
 * @author MrBird
 */
public interface RoleMapper extends BaseDao<Role,String> {

    /**
     * 查询用户列表
     * @return
     */
    List<GetRoleListRes> getRoleList(String eId);

    /**
     * 查询用户详情
     * @return
     */
    GetRoleRes getRole(String roleId);

    /**
     * 查询角色详情
     * @return
     */
    Role selectByKey(Role role);

    /**
     * 根据企业id角色id删除记录
     * @return
     */
    void deleteByKey(String eId,String roleId);
}
