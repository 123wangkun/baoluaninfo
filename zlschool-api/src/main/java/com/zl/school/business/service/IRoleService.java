package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */
public interface IRoleService extends IBaseService<Role,String> {
    /**
     * 查询角色列表
     * @return
     */
    List<GetRoleListRes> getRoleList(HttpServletRequest request);

    /**
     * 查询角色权限目录树
     * @return
     */
    GetMenuTreeRes rolePower(String roleId,HttpServletRequest request);

    /**
     * 进入添加角色界面
     * @return
     */
    GetAllMenuTreeRes getAllMenuTree(HttpServletRequest request);

    /**
     * 新增角色
     * @return
     */
    void addRole(AddRoleReq addRoleReq,HttpServletRequest request);

    /**
     * 修改角色
     * @return
     */
    void editRole(EditRoleReq editRoleReq,HttpServletRequest request);

    /**
     * 删除角色
     * @return
     */
    void deleteRole(String roleId,HttpServletRequest request);
}
