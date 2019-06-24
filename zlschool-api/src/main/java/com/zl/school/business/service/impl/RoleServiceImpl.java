package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.system.MenuMapper;
import com.zl.school.business.dao.system.RolePowerMapper;
import com.zl.school.business.dao.system.UserPowerMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.Menu;
import com.zl.school.business.entity.system.Role;
import com.zl.school.business.dao.system.RoleMapper;
import com.zl.school.business.entity.system.RolePower;
import com.zl.school.business.service.IRoleService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zl.school.business.dto.system.GetMenuTreeRes.RolePowerMenu;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.zl.school.business.dto.system.GetAllMenuTreeRes.AllMenuTree;
/**
 * @author MrBird
 */
@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role,String> implements IRoleService {

    @Autowired
    private RoleMapper mapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RolePowerMapper rolePowermapper;
    @Autowired
    private UserPowerMapper userPowerMapper;
    public RoleServiceImpl(RoleMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询角色列表
     * @return
     */
    @Override
    public List<GetRoleListRes> getRoleList(HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        List<GetRoleListRes> orders = mapper.getRoleList(eId);
        return orders;
    }

    /**
     * 根据用户id查询角色详情
     * @return
     */
    @Override
    public GetMenuTreeRes rolePower(String roleId,HttpServletRequest request){
        GetMenuTreeRes menuTreeRes = new GetMenuTreeRes();
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        Role role = new Role();
        role.setEid(eId);
        role.setId(roleId);
        role = mapper.selectByKey(role);
        //查询所有的功能菜单列表
        List<Menu>  menuList = menuMapper.selectAllMenu();
        RolePower rolePower = new RolePower();
        rolePower.setEid(eId);
        rolePower.setRoleId(roleId);
        List<RolePower>  rolePowerList = rolePowermapper.selectByKey(rolePower);
        // 3:判断角色有哪些菜单功能权限，并把checked设为TRUE，否则为false,选择该用户已有角色
        List<RolePowerMenu> rolePowerMenuList = new ArrayList();
        //循环将角色菜单转换成GetMenuTreeRes列表
        if (menuList.size() > 0 ) {
            for (Menu menu : menuList) {
                RolePowerMenu menuTree = new RolePowerMenu();
                menuTree.setId(menu.getId());
                menuTree.setName(menu.getName());
                menuTree.setParentId(menu.getParentId());
                menuTree.setDescribe(menu.getMemo());
                menuTree.setNote(menu.getNote());
                menuTree.setType(menu.getType());
                for (RolePower rolePowerValue : rolePowerList) {
                    //如果该角色拥有的菜单id等于所有菜单列表的id，将勾选状态显示为TRUE
                    if (rolePowerValue.getMenuId().equals(menu.getId())) {
                        menuTree.setChecked(true);
                    }
                }
                rolePowerMenuList.add(menuTree);
            }
        }
        // 转换为树结构
        List treeRolePowerList = this.getRolePowerTreeByList(rolePowerMenuList);
        menuTreeRes.setRolePowerTree(treeRolePowerList);
        menuTreeRes.setRoleId(role.getId());
        menuTreeRes.setRoleName(role.getName());
        menuTreeRes.setMemo(role.getMemo());
        return menuTreeRes;
    }

    /**
     * 修改角色时显示的菜单目录树----角色菜单列表结构转换成树结构
     */
    public List getRolePowerTreeByList(List<RolePowerMenu> roleMenuList){
        List<RolePowerMenu> treeRolePowerList = new ArrayList<RolePowerMenu>();
        for (RolePowerMenu pValue : roleMenuList) {
            if (pValue.getParentId().equals("root")) {
                treeRolePowerList.add(pValue);
            }
            for (RolePowerMenu cValue : roleMenuList) {
                if (cValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<RolePowerMenu> childList = new ArrayList<RolePowerMenu>();
                        childList.add(cValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(cValue);
                    }
                }
            }
        }
        return treeRolePowerList;
    }

    /**
     * 查询菜单目录树
     * @return
     */
    @Override
    public GetAllMenuTreeRes getAllMenuTree(HttpServletRequest request){
        GetAllMenuTreeRes getAllMenuTree = new GetAllMenuTreeRes();
        //查询所有的功能菜单列表
        List<Menu>  menuList = menuMapper.selectAllMenu();
        List<AllMenuTree> rolePowerMenuList = new ArrayList();
        //循环将角色菜单转换成GetMenuTreeRes列表
        if (menuList.size() > 0 ) {
            for (Menu menu : menuList) {
                AllMenuTree menuTree = new AllMenuTree();
                menuTree.setId(menu.getId());
                menuTree.setName(menu.getName());
                menuTree.setParentId(menu.getParentId());
                menuTree.setDescribe(menu.getMemo());
                menuTree.setNote(menu.getNote());
                rolePowerMenuList.add(menuTree);
            }
        }
        // 转换为树结构
        List treeRolePowerList = this.getAllMenuTreeByList(rolePowerMenuList);
        getAllMenuTree.setAddRoleMenu(treeRolePowerList);
        return getAllMenuTree;
    }


    /**
     * 进入添加角色时的菜单列表   所有菜单列表列表结构转换成树结构
     */
    public List getAllMenuTreeByList(List<AllMenuTree> roleMenuList){
        List<AllMenuTree> treeRolePowerList = new ArrayList<AllMenuTree>();
        for (AllMenuTree pValue : roleMenuList) {
            if (pValue.getParentId().equals("root")) {
                treeRolePowerList.add(pValue);
            }
            for (AllMenuTree cValue : roleMenuList) {
                if (cValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<AllMenuTree> childList = new ArrayList<AllMenuTree>();
                        childList.add(cValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(cValue);
                    }
                }
            }
        }
        return treeRolePowerList;
    }

    /**
     * 新增角色
     * @return
     */
    @Override
    public void addRole(AddRoleReq addRoleReq,HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        Role role = new Role();
        role.setId(UuidUtil.getRandomUuid());
        role.setEid(eId);
        role.setName(addRoleReq.getName());
        role.setMemo(addRoleReq.getMemo());
        role.setEid(eId);
        //保存角色信息到角色表
        mapper.insertSelective(role);
        //获取前端传过来的菜单列表
        List<AddRoleReq.MenuList> menuIds = addRoleReq.getMenuIds();
        //循环保存关联信息到角色菜单表
        for(AddRoleReq.MenuList menus : menuIds){
            RolePower rolePower = new RolePower();
            rolePower.setEid(eId);
            rolePower.setRoleId(role.getId());
            //获取角色列表里面的角色id
            rolePower.setMenuId(menus.getMenuId());
            //添加角色菜单表记录
            rolePowermapper.insert(rolePower);
        }
    }

    /**
     * 修改角色
     * @return
     */
    @Override
    public void editRole(EditRoleReq editRoleReq,HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        Role role = new Role();
        role.setEid(eId);
        role.setId(editRoleReq.getId());
        role = mapper.selectByKey(role);
        role.setName(editRoleReq.getName());
        role.setMemo(editRoleReq.getMemo());
        role.setEid(eId);
        mapper.updateByPrimaryKeySelective(role);//根据主键更新记录
        //查询统计该企业下的该角色总共拥有多少条菜单记录
        int size = rolePowermapper.selectCount(eId,editRoleReq.getId());
        //根据企业id和用户id删除该角色的原有菜单记录
        if(size>0){
            rolePowermapper.deleteRoles(eId,editRoleReq.getId());
        }
        List<EditRoleReq.MenuList> menuIds = editRoleReq.getMenuIds();//获取前端传过来的角色列表
        //循环保存菜单关联信息到角色菜单表
        for(EditRoleReq.MenuList menus : menuIds){
            RolePower rolePower = new RolePower();
            rolePower.setEid(eId);
            rolePower.setRoleId(role.getId());
            //获取角色列表里面的菜单id
            rolePower.setMenuId(menus.getMenuId());
            //添加角色菜单表记录
            rolePowermapper.insert(rolePower);
        }
    }

    /**
     * 删除角色
     * @return
     */
    @Override
    public void deleteRole(String roleId,HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //查询统计该角色有没有关联用户
        int userSize = userPowerMapper.selectCount(eId,roleId);
        if(userSize>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该角色已被用户使用，不能删除！");
        }else {
            //查询统计该企业下的该角色总共拥有多少条菜单记录
            int size = rolePowermapper.selectCount(eId, roleId);
            //根据企业id和用户id循环删除该角色的原有菜单记录
            if (size > 0) {
                rolePowermapper.deleteRoles(eId, roleId);
            }
            //根据企业id角色id删除记录
            mapper.deleteByKey(eId, roleId);
        }
    }
}
