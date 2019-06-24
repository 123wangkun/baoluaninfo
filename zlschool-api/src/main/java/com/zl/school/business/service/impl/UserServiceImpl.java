package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.system.*;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.*;
import com.zl.school.business.service.IUserService;
import com.zl.school.business.util.MD5Util;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.zl.school.business.dto.system.GetUserRes.RoleList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MrBird
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, String> implements IUserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserPowerMapper userPowerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private RoleMapper roleMapper;
    public UserServiceImpl(UserMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 根据企业id查询所有用户列表
     * @return
     */
    @Override
    public RspPage<GetUserListRes> getUserList(GetUserListReq getUserListReq, HttpServletRequest request){
        //如果前端不传值则默认查询该企业
        if(getUserListReq.getDepartId().isEmpty()){
            HttpSession session = request.getSession();
            String eId = (String) session.getAttribute("eId");
            getUserListReq.setEid(eId);
        }

        //根据企业id查询用户   多表查询
        List<GetUserListRes> orders = mapper.getUserList(getUserListReq);
        for(GetUserListRes getUserListRes : orders){
            if(getUserListRes.getStatus() == EnterpriseUser.STATUS_NORMAL){
                getUserListRes.setStatusName("启用");
            }else{
                getUserListRes.setStatusName("禁用");
            }

            if(getUserListRes.getRegStatus() == EnterpriseUser.STATUS_REG){
                getUserListRes.setRegStatusName("已注册");
            }else{
                getUserListRes.setRegStatusName("未注册");
            }
        }

        //分页
        RspPage<GetUserListRes> rspPage = new RspPage<>();
        Page page = PageHelper.startPage(getUserListReq.getPageNum(), getUserListReq.getPageSize(), true);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询所有个人用户列表
     * @return
     */
    @Override
    public RspPage<GetPersonalUserListRes> getPersonalUserList(GetPersonalUserListReq getPersonalUserListReq, HttpServletRequest request){
        RspPage<GetPersonalUserListRes> rspPage = new RspPage<>();
        Page page = PageHelper.startPage(getPersonalUserListReq.getPageNum(), getPersonalUserListReq.getPageSize(), true);
        getPersonalUserListReq.setEId("00");
        //根据企业id查询用户   多表查询
        List<GetPersonalUserListRes> orders = mapper.getPersonalUserList(getPersonalUserListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询用户详情
     * @return
     */
    @Override
    public GetUserRes getUser(String userId,HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        GetUserRes getUserRes = mapper.getUser(userId,eId);
        //查询出该企业下的所有角色列表
        List<GetRoleListRes> allRole = roleMapper.getRoleList(eId);
        //根据企业id、用户id查询出所拥有的角色
        UserPower userPower = new UserPower();
        userPower.setEid(eId);
        userPower.setUserId(userId);
        List<UserPower> userPowerList = userPowerMapper.selectByKey(userPower);
        List<RoleList> roleLists=  new ArrayList();
        if (allRole.size() > 0 ) {
            for (GetRoleListRes role : allRole) {
                RoleList roles = new RoleList();
                roles.setId(role.getId());
                roles.setName(role.getName());
                for (UserPower userPowerValue : userPowerList) {
                    //如果该用户拥有的角色id等于所有角色列表的id，将勾选状态显示为TRUE
                    if (userPowerValue.getRoleId().equals(role.getId())) {
                        roles.setChecked(true);
                    }
                }
                roleLists.add(roles);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "您尚未添加角色");
        }
        //将已设置好勾选状态的所有角色列表放到DTO返回
        getUserRes.setRoleList(roleLists);
        return getUserRes;
    }
    /**
     * 新增用户
     * @return
     */
    @Override
    public void addUser(AddUserReq addUserReq,HttpServletRequest request){
        Enterprise enterprise = null;
        EnterpriseUser enterpriseUser = null;
        User user =null;
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //根据手机号查询用户信息
        user = userMapper.getUserByPhone(addUserReq.getTelePhone());
        if(user != null) {
            //用户存在
            //根据企业id/部门id和员工id查询是否已添加过该用户
            //根据用户id和企业id去查询该手机号是否已存在个人用户
            enterpriseUser = enterpriseUserMapper.selectEntUser(eId, user.getId());
            if (enterpriseUser != null) {
                //说明已经添加过了，不需要再次添加
                throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "已添加过该用户，无需再次添加！");
            }
        }else{
            //用户不存在
            //添加该用户到user表
            user = new User();
            user.setId(UuidUtil.getRandomUuid());
            user.setPassword(addUserReq.getPassword());
            user.setRealName(addUserReq.getRealName());
            user.setTelePhone(addUserReq.getTelePhone());
            user.setEmail(addUserReq.getEmail());
            user.setMemo(addUserReq.getMemo());
            user.setSex(addUserReq.getSex());
            user.setAge(addUserReq.getAge());
            user.setLastEid(eId);
            //保存用户信息到用户表
            mapper.insertSelective(user);
            //添加该用户到企业员工表
            //添加该用户到用户角色表
        }
        //添加企业员工表记录
        insertEntUser(eId,addUserReq,user.getId());
        //获取前端传过来的角色列表
        List<AddUserReq.RoleDto> roleIds = addUserReq.getRoleList();
        //循环保存关联信息到用户角色表
        for(AddUserReq.RoleDto roleDto : roleIds){
            UserPower userPower = new UserPower();
            userPower.setEid(eId);
            userPower.setUserId(user.getId());
            //设置角色
            userPower.setRoleId(roleDto.getRoleId());
            //添加用户角色表记录
            userPowerMapper.insert(userPower);
        }
    }

    /**
     * 修改企业用户
     * @return
     */
    @Override
    public void editUser(EditUserReq editUserReq){
        User user = null;
        User userByPhone = null;
        if(editUserReq.getId().isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PASSWORD, "用户编号不可为空");
        }
        //根据用户id查询出该用户旧记录
        user = mapper.selectByPrimaryKey(editUserReq.getId());
        //如果用户修改的手机号不等于原来的手机号
        if(!editUserReq.getTelePhone().equals(user.getTelePhone())){
            //根据新的手机号查询数据库是否已存在
            //根据手机号查询用户信息
            userByPhone = userMapper.getUserByPhone(editUserReq.getTelePhone());
            if(userByPhone != null){
                throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "该手机号已被注册");
            }
        }
        user.setId(editUserReq.getId());
        user.setPassword(editUserReq.getPassword());
        user.setRealName(editUserReq.getRealName());
        user.setTelePhone(editUserReq.getTelePhone());
        user.setSex(editUserReq.getSex());
        user.setAge(editUserReq.getAge());
        user.setMemo(editUserReq.getMemo());
        user.setDegree (editUserReq.getDegree());
        user.setEducation (editUserReq.getEducation());
        user.setEmail(editUserReq.getEmail());
        mapper.updateByPrimaryKeySelective(user);//根据主键更新记录
        //查询统计该企业下的该用户总共拥有多少个角色
        int size = userPowerMapper.selectCount(editUserReq.getEid(),editUserReq.getId());
        //根据企业id和用户id循环删除该用户的原有角色记录
        for(int i=0;i<size;i++){
            userPowerMapper.deleteRoles(editUserReq.getEid(),editUserReq.getId());
        }
        List<EditUserReq.RoleList> roleIds = editUserReq.getRoleIds();//获取前端传过来的角色列表
        //循环保存新的角色信息到用户角色表
        for(EditUserReq.RoleList roles : roleIds){
            UserPower userPower = new UserPower();
            userPower.setEid(editUserReq.getEid());
            userPower.setUserId(user.getId());
            //获取角色列表里面的角色id
            userPower.setRoleId(roles.getRoleId());
            //添加用户角色表记录
            userPowerMapper.insert(userPower);
        }
    }

    /**
     * 删除用户（组织管理的删除用户以及个人管理的删除用户）
     * @return
     */
    @Override
    public void deleteUser(DeleteUserReq deleteUserReq, HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //根据用户id以及企业id查询该企业用户是否已注册，注册后不能删除
        EnterpriseUser  entUser= enterpriseUserMapper.selectEntUser(eId, deleteUserReq.getUserId());
        if(entUser !=null ) {
            //删除企业员工表记录
            EnterpriseUser enterpriseUser = new EnterpriseUser();
            enterpriseUser.setEid(eId);
            enterpriseUser.setUserId(deleteUserReq.getUserId());
            enterpriseUserMapper.deleteEntUser(enterpriseUser);
            //统计该用户关联的角色总共有多少个
            int size = userPowerMapper.selectCount(eId, deleteUserReq.getUserId());
            if (size > 0) {
                //循环删除企业用户角色表记录
                userPowerMapper.deleteRoles(eId, deleteUserReq.getUserId());
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "该用户已注册使用，不能删除！");
        }
    }


    /**
     * 修改密码
     * @return
     */
    @Override
    public void EditPassword(EditPasswordReq editPasswordReq){
        String encryptPassword = MD5Util.encrypt(editPasswordReq.getNewPassword());//MD5加密
        User user = mapper.getUserByPhone(editPasswordReq.getTelePhone());
        //修改密码
        String oldPassword = MD5Util.encrypt(editPasswordReq.getOldPassword());//MD5加密
        if(user != null) {
            if (user.getPassword().equals(oldPassword)) {
                //输入的原密码正确
                //修改密码
                user.setPassword(encryptPassword);
            } else {
                throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "输入的密码与原密码不符");
            }
            try {
                mapper.updateByPrimaryKeySelective(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "更新密码失败");
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改密码失败，该手机号尚未注册！");
        }
    }

    /**
     * 添加企业用户记录
     * @return
     */
    public void insertEntUser(String eId,AddUserReq addUserReq,String userId){
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setEid(eId);//企业编码
        enterpriseUser.setDepartId(addUserReq.getDepId());//部门id
        enterpriseUser.setUserId(userId);
        enterpriseUser.setIntegral(0);
        enterpriseUser.setPosition(addUserReq.getPosition());
        enterpriseUser.setPost(addUserReq.getPost());
        //设置用户允许上传文件总大小为204800KB
        enterpriseUser.setAllowSize((long) 204800);
        enterpriseUser.setMakeSize(0L);
        enterpriseUser.setRegStatus(EnterpriseUser.STATUS_NotREG);
        enterpriseUser.setStatus(addUserReq.getStatus());
        enterpriseUser.setCreatedTime(new Date());
        enterpriseUser.setModifydTime(new Date());
        enterpriseUserMapper.insertSelective(enterpriseUser);
    }

    /**
     * 查询个人用户详情
     * @return
     */
    @Override
    public GetPersonalRes getPersonal(String userId, HttpServletRequest request){
       //根据个人企业id以及用户id查询用户信息
        GetPersonalRes getPersonal = mapper.getPersonal(userId,"00");
        return getPersonal;
    }

    /**
     * 修改个人用户信息
     * @return
     */
    @Override
    public void editPersonal(GetPersonalReq personalReq){
        //根据个人企业id以及用户id查询用户信息
        User user  = mapper.selectByPrimaryKey(personalReq.getId());
        if(user != null){
            user.setRealName(personalReq.getRealName());
            user.setTelePhone(personalReq.getTelePhone());
            mapper.updateByPrimaryKeySelective(user);//根据主键更新记录
            EnterpriseUser entUser = enterpriseUserMapper.selectEntUser("00",personalReq.getId());
            if(entUser != null){
                entUser.setAllowSize(personalReq.getAllowSize());
                entUser.setStatus(personalReq.getStatus());
                enterpriseUserMapper.updateByPrimaryKeySelective(entUser);
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改用户失败！");
        }

    }

}
