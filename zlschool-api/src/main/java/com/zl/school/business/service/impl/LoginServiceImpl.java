package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.certificate.CertificateMapper;
import com.zl.school.business.dao.system.*;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.certificate.Certificate;
import com.zl.school.business.entity.system.*;
import com.zl.school.business.service.ILoginService;
import com.zl.school.business.util.MD5Util;
import com.zl.school.business.util.StringUtil;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zl.school.business.dto.system.GetUserPowerRes.UserPowerTree;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MrBird
 */
@Service
public class LoginServiceImpl extends AbstractServiceImpl<User, String> implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPowerMapper userPowerMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private CertificateMapper certificateMapper;
    @Autowired
    private MenuMapper menuMapper;
    public LoginServiceImpl(LoginMapper loginMapper) {
        super(loginMapper);
        this.loginMapper = loginMapper;
    }

    /**
     * 用户登陆
     * @return
     */
    @Override
    public LoginRes login(LoginReq loginReq,HttpServletRequest request) {
        //1：根据手机号查询用户信息
        User user = loginMapper.getUserByPhone(loginReq);

        if(user != null){
            //2:根据用户最后一次登陆企业信息,查询企业用户表
            Enterprise enterprise = null;
            EnterpriseUser enterpriseUser = null;
            if(user.getLastEid().isEmpty()){
                enterpriseUser = enterpriseUserMapper.selectEntUser(EnterpriseUser.Default_Personal, user.getId());
            }else{
                enterpriseUser = enterpriseUserMapper.selectEntUser(user.getLastEid(), user.getId());
            }

            //3:查询企业信息表
            enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseUser.getEid());
            if(enterprise != null){
                //判断企业是否有效
                if((new Date()).getTime() > enterprise.getValidate().getTime()){
                    throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "该企业不在有效期内,请联系管理员!");
                }
            }

            //4:判断用户状态
            if(enterpriseUser.getRegStatus() != EnterpriseUser.STATUS_REG){
                throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "您还未注册,请先注册!");
            }
            if(enterpriseUser.getStatus() == EnterpriseUser.STATUS_CANCEL){
                throw new BusinessException(ErrorCode.ERROR_STATUS, "您账户已经被注销,请联系后台管理员");
            }

            //5:判断用户名密码
            String reqPassword = MD5Util.encrypt(loginReq.getPassword());//MD5加密
            if (user.getPassword().equals(reqPassword)) {
                //5.1修改登录时间
                user.setLastLogin(new Date());
                userMapper.updateByPrimaryKeySelective(user);

                //5.2将登陆者信息保存进session
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                session.setAttribute("eId",user.getLastEid());
                session.setAttribute("userId",user.getId());
                session.setMaxInactiveInterval(30 * 60);//设置session超时时间为30分钟

                //5.3组装返回数据
                LoginRes loginRes = new LoginRes();
                loginRes.setEid(user.getLastEid());
                loginRes.setTelePhone(user.getTelePhone());
                loginRes.setPassword(user.getPassword());
                return loginRes;
            } else {
                throw new BusinessException(ErrorCode.ERROR_PASSWORD, "密码错误");
            }
        }else{
            throw new BusinessException(ErrorCode.NO_PHONE_EXIST, "账号不存在");
        }
    }

    /**
     * 用户注册
     * @return
     */
    @Override
    public void register(RegisterReq registerReq){
        //1:校验参数是否正确
        if(registerReq.getTelePhone().isEmpty() || registerReq.getTelePhone().length() != 11){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "手机号码错误");
        }

        //2:定义变量
        User user = null;
        Enterprise enterprise = null;
        Department department = null;
        EnterpriseUser enterpriseUser = null;
        String eid = null;

        //3:根据手机号查user
        user = userMapper.getUserByPhone(registerReq.getTelePhone());

        //4:汇总上传文件总大小
        Long totalSize = 0L;
        if(registerReq.getFileList()!=null) {
            for (int i = 0; i < registerReq.getFileList().size(); i++) {
                //循环保存文件到证书表(循环累加统计所上传文件的总大小)
                Long size = registerReq.getFileList().get(i).getSize();
                totalSize += size;
            }
        }
        //5:判断是否是个人用户
        if(StringUtil.isNotEmpty(registerReq.getEntCode())){
            //5.1:企业用户--根据邀请码查询出对应部门的记录
            department = departmentMapper.selectDepByCode(registerReq.getEntCode());
            if(department == null){
                throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "部门邀请码不正确");
            }
            //根据企业id查询企业信息
            enterprise = enterpriseMapper.selectByPrimaryKey(department.getEid());
            //5.2:查询该部门的顶级企业（root）
            if(enterprise == null){
                throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "企业信息不存在,请联系管理员!");
            }

            //判断企业是否有效
            if(enterprise.getStatus() != Enterprise.Default_Pass){
                throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "该企业未启用,请联系管理员!");
            }
            if((new Date()).getTime() > enterprise.getValidate().getTime()){
                throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "该企业不在有效期内,请联系管理员!");
            }
            //5.3:设置企业id,用于保存用户证书
            eid = enterprise.getId();
            //5.4:判断用户是否已近存在
            if(user != null){
                //用户已存在(1:本企业导入 2:本企业已注册过 3:其它企业注册过)
                //根据用户id和企业id去查询该手机号是否已存在该企业用户
                enterpriseUser = enterpriseUserMapper.selectEntUser(enterprise.getId(), user.getId());
                if (enterpriseUser != null) {
                    //该企业已存在该用户
                    if(enterpriseUser.getRegStatus() == EnterpriseUser.STATUS_NotREG){
                        //被企业导入的用户，尚未完善信息
                        //1:更新用户信息
                        user = updateUser(registerReq, user, enterprise.getId());

                        //2;更新企业用户表
                        enterpriseUser.setMakeSize(enterpriseUser.getMakeSize() + totalSize);
                        enterpriseUser.setRegStatus(EnterpriseUser.STATUS_REG);
                        enterpriseUser.setType(EnterpriseUser.TYPE_ENTER);
                        enterpriseUser.setStatus(EnterpriseUser.STATUS_NORMAL);
                        enterpriseUser.setModifydTime(new Date());
                        enterpriseUserMapper.updateByPrimaryKeySelective(enterpriseUser);

                        //4.更新企业信息表
                        //计算该员工所属企业当前已用空间大小
                        Long size = enterprise.getMakeSize() + totalSize;//kb
                        if(size > enterprise.getAllowSize()){
                            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "该企业的文件存储空间已达上限,请联系管理员!");
                        }
                        enterprise.setMakeSize(size);
                        //更新企业信息
                        enterpriseMapper.updateByPrimaryKeySelective(enterprise);
                    }else{
                        //已注册，已加入所输入的邀请码所对应的企业
                        throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "您已加入该企业！");
                    }
                }else{
                    //不存在， 说明是已加入过其它企业,还未加入本企业
                    if(registerReq.getPassword()!=null){
                        //为空，说明是加入组织，不为空，说明是注册
                        //1:更新用户信息
                        user = updateUser(registerReq, user, enterprise.getId());
                    }
                    //2.保存用户信息到用户角色表(角色为企业用户)
                    insertUserPower(user,enterprise.getId(),Role.ROLE_STAFF);

                    //3;新增企业用户表
                    insertEnterpriseUser(user, eid,department.getId(), EnterpriseUser.TYPE_ENTER, totalSize,registerReq);

                    //4.更新企业信息表
                    updateEnterprise(enterprise, totalSize);
                }
            }else{
                //还没有关联任何企业，也没有注册过
                //1.保存用户信息到用户表和用户角色信息
                user = insertUser(registerReq,eid);

                //2.保存用户角色信息
                insertUserPower(user,enterprise.getId(),Role.ROLE_STAFF);

                //2;新增企业用户表
                 insertEnterpriseUser(user, eid,department.getId(), EnterpriseUser.TYPE_ENTER, totalSize,registerReq);

                //3.更新企业信息表
                 updateEnterprise(enterprise, totalSize);
            }
        } else {
            //个人用户
            if (user != null) {
                //该用户已在其它企业注册,根据用户id和企业id去查询该手机号是否已存在个人用户
                enterpriseUser = enterpriseUserMapper.selectEntUser(EnterpriseUser.Default_Personal, user.getId());
                //已存在个人用户
                if (enterpriseUser != null) {
                    throw new BusinessException(ErrorCode.DATA_NOT_FOUND, "该手机号已注册");
                }else{
                    //1:更新用户信息
                    user = updateUser(registerReq, user, EnterpriseUser.Default_Personal);

                    //2:保存用户信息到用户角色表(个人用户)
                    insertUserPower(user,EnterpriseUser.Default_Personal,Role.ROLE_PERSON);
                }
            } else {
                //1.保存用户信息到用户表和用户角色信息
                user = insertUser(registerReq,EnterpriseUser.Default_Personal);

                //2.保存用户角色信息
                insertUserPower(user,EnterpriseUser.Default_Personal,Role.ROLE_PERSON);
            }

            //3;用户已在其它企业注册,但未注册个人用户,新增企业用户表
            insertEnterpriseUser(user, EnterpriseUser.Default_Personal,EnterpriseUser.Default_Personal, EnterpriseUser.TYPE_PERSON, totalSize,registerReq);

            //4:设置企业id,用于保存用户证书
            eid = EnterpriseUser.Default_Personal;
        }
        if(registerReq.getFileList()!=null) {
            //6:保存用户的证书信息
            insertCertificate(registerReq, user, eid);
        }
    }

    /**
     * 注册用户--新增用户
     * @return
     */
    public User insertUser(RegisterReq registerReq, String eid){
        //保存用户信息到用户表
        User user = new User();
        user.setId(UuidUtil.getRandomUuid());
        String encryptPassword = MD5Util.encrypt(registerReq.getPassword());//MD5加密
        user.setPassword(encryptPassword);//加密后保存
        user.setRealName(registerReq.getRealName());
        user.setTelePhone(registerReq.getTelePhone());
        user.setEmail(registerReq.getEmail());
        user.setAge(registerReq.getAge());
        user.setSex(registerReq.getSex());
        user.setCreatedTime(new Date());
        user.setModifydTime(new Date());
        user.setLastEid(eid);
        userMapper.insertSelective(user);//添加用户

        return user;
    }

    /**
     * 注册用户--新增用户角色信息
     * @return
     */
    public void insertUserPower(User user, String eid, String roleId){
        //保存用户信息到用户角色表(角色为个人用户)
        UserPower userPower = new UserPower();
        userPower.setEid(eid);//设置企业id
        userPower.setRoleId(roleId);//设置该用户所属角色id
        userPower.setUserId(user.getId());
        userPowerMapper.insert(userPower);//添加用户角色表记录
    }

    /**
     * 注册用户--新增企业用户
     * @return
     */
    public void insertEnterpriseUser(User user, String eid,String depId, int type, long totalSize,RegisterReq registerReq){
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setEid(eid);//企业编码
        enterpriseUser.setDepartId(depId);//部门id
        enterpriseUser.setUserId(user.getId());
        //设置用户允许上传文件总大小为204800KB
        enterpriseUser.setAllowSize((long) 204800);
        enterpriseUser.setMakeSize(totalSize);
        enterpriseUser.setRegStatus(EnterpriseUser.STATUS_REG);
        enterpriseUser.setType(type);
        enterpriseUser.setPosition(registerReq.getPosition());
        enterpriseUser.setPost(registerReq.getPost());
        enterpriseUser.setStatus(EnterpriseUser.STATUS_NORMAL);
        enterpriseUser.setCreatedTime(new Date());
        enterpriseUser.setModifydTime(new Date());
        enterpriseUserMapper.insert(enterpriseUser);
    }

    /**
     * 注册用户--新增用户证书信息
     * @return
     */
    public void insertCertificate(RegisterReq registerReq, User user, String eid){
        Certificate certificate = null;
        RegisterReq.FileDTO fileDTO = null;
        for(int i=0;i<registerReq.getFileList().size();i++){
            fileDTO = registerReq.getFileList().get(i);
            //循环保存文件到证书表
            certificate = new Certificate();
            certificate.setId(UuidUtil.getRandomUuid());
            certificate.setEid(eid);//设置企业id
            certificate.setUserId(user.getId());
            certificate.setName(fileDTO.getName());
            certificate.setTypeId(fileDTO.getType());
            certificate.setFileSrc(fileDTO.getUrl());
            certificate.setFileSize(fileDTO.getSize());
            certificate.setState(Certificate.status_wait);
            certificate.setCreatedTime(new Date());
            certificate.setCreateUser(user.getId());
            certificateMapper.insertSelective(certificate);
        }
    }

    /**
     * 注册用户--更新企业信息
     * @return
     */
    public void updateEnterprise(Enterprise enterprise, long totalSize){
        //计算该员工所属企业当前已用空间大小
        Long size = enterprise.getMakeSize() + totalSize;//kb
        //计算该员工所属企业当前已有用户数
        int makeUser = enterprise.getMakeUser() + 1;//用户数+1
        if(size > enterprise.getAllowSize()){
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "该企业的文件存储空间已达上限,请联系管理员!");
        }
        if(makeUser > enterprise.getAllowUser()){
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "该企业的用户人数已达上限,请联系管理员!");
        }
        enterprise.setMakeSize(size);
        enterprise.setMakeUser(makeUser);
        //更新企业信息
        enterpriseMapper.updateByPrimaryKeySelective(enterprise);
    }

    /**
     * 注册用户--更新用户信息
     * @return
     */
    public User updateUser(RegisterReq registerReq, User user, String eid){
        String encryptPassword = MD5Util.encrypt(registerReq.getPassword());//MD5加密
        user.setPassword(encryptPassword);//加密后保存
        user.setRealName(registerReq.getRealName());
        user.setTelePhone(registerReq.getTelePhone());
        user.setEmail(registerReq.getEmail());
        user.setAge(registerReq.getAge());
        user.setSex(registerReq.getSex());
        user.setModifydTime(new Date());
        user.setLastEid(eid);
        userMapper.updateByPrimaryKeySelective(user);//更新用户

        return user;
    }

    /**
     * 忘记密码
     * @return
     */
    @Override
    public void ForgetPassword(ForgetPasswordReq forgetPasswordReqReq){
        String encryptPassword = MD5Util.encrypt(forgetPasswordReqReq.getNewPassword());//MD5加密
        User user = userMapper.getUserByPhone(forgetPasswordReqReq.getTelePhone());
        if(user != null) {
            user.setPassword(encryptPassword);
            try {
                userMapper.updateByPrimaryKeySelective(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "更新密码失败");
            }
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "找回密码失败，该手机号尚未注册！");
        }
    }

    /**
     * 查询用户登陆session
     * @return
     */
    @Override
    public GetSessionRes getSession(HttpServletRequest request){
        // 1：去sessiopn中获取用户
        User user = (User)request.getSession().getAttribute("user");
        String eid = (String)request.getSession().getAttribute("eId");
        // 2：把用户信息放入response中
        GetSessionRes getSessionRes = new GetSessionRes();
        GetSessionRes.UserDto userDto = new GetSessionRes.UserDto();
        if(user != null){
            userDto.setEid(eid);
            userDto.setId(user.getId());
            userDto.setRealName(user.getRealName());
            userDto.setTelePhone(user.getTelePhone());
            userDto.setEmail(user.getEmail());
            userDto.setAge(user.getAge());
            getSessionRes.setUser(userDto);
        }

        return getSessionRes;
    }

    /**
     * 进入系统时，根据用户查询用户菜单权限
     * @return
     */
    @Override
    public GetUserPowerRes getUserPower(HttpServletRequest request){
        // 去sessiopn中获取用户编号跟企业编号
        String userId = (String)request.getSession().getAttribute("userId");
        String eId = (String)request.getSession().getAttribute("eId");
        GetUserPowerRes getUserPowerRes = new GetUserPowerRes();
        //查询所有的功能菜单列表
        List<Menu> menuList = menuMapper.selectAllMenuByUser(userId,eId);
        List<UserPowerTree> userPowerList = new ArrayList();
        //循环将角色菜单转换成GetMenuTreeRes列表
        if (menuList.size() > 0 ) {
            for (Menu menu : menuList) {
                UserPowerTree menuTree = new UserPowerTree();
                menuTree.setId(menu.getId());
                menuTree.setName(menu.getName());
                menuTree.setParentId(menu.getParentId());
                menuTree.setDescribe(menu.getMemo());
                menuTree.setNote(menu.getNote());
                userPowerList.add(menuTree);
            }
        }
        // 转换为树结构
        List userPowerTree = this.getMenuTreeByList(userPowerList);
        getUserPowerRes.setUserPowerTree(userPowerTree);
        return getUserPowerRes;
    }

    /**
     * 用户菜单权限列表转换成目录树
     */
    public List getMenuTreeByList(List<UserPowerTree> userPowerList){
        List<UserPowerTree> userPowerTree = new ArrayList<UserPowerTree>();
        for (UserPowerTree pValue : userPowerList) {
            if (pValue.getParentId().equals("root")) {
                userPowerTree.add(pValue);
            }
            for (UserPowerTree cValue : userPowerList) {
                if (cValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<UserPowerTree> childList = new ArrayList<UserPowerTree>();
                        childList.add(cValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(cValue);
                    }
                }
            }
        }
        return userPowerTree;
    }

    /**
     * 根据菜单id查询用户操作权限，即按钮
     * @return
     */
    @Override
    public List<GetUserOperatorRes> getUserOperator(String menuId,HttpServletRequest request){
        // 去sessiopn中获取用户编号跟企业编号
        String userId = (String)request.getSession().getAttribute("userId");
        String eId = (String)request.getSession().getAttribute("eId");
        List<GetUserOperatorRes> resList = new ArrayList<>();
        //查询所有的功能菜单列表
        List<Menu> menuList = menuMapper.selectAllMenuByUser(userId,eId);
        for (Menu menuValue : menuList) {
            //判断菜单的父级id是否为传过来的菜单id
            if(menuValue.getParentId().equals(menuId)){
                //是，则保存按钮信息到DTO
                GetUserOperatorRes res = new GetUserOperatorRes();
                res.setId(menuValue.getId());
                res.setName(menuValue.getName());
                res.setParentId(menuValue.getParentId());
                resList.add(res);
            }
        }
        return resList;
    }

    /**
     * 退出登陆
     * @return
     */
    @Override
    public void loginOut(HttpServletRequest request){
        // 1：清除session值
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("eid");
        session.removeAttribute("userId");
    }


}
