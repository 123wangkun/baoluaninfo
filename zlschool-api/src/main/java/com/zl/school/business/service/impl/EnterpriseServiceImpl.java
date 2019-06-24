package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.dao.system.*;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.*;
import com.zl.school.business.service.IEnterpriseService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zl.school.business.common.exception.ErrorCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author MrBird
 */
@Service
public class EnterpriseServiceImpl extends AbstractServiceImpl<Enterprise,String> implements IEnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPowerMapper userPowerMapper;
    @Autowired
    private RoleMapper roleMapper;

    public EnterpriseServiceImpl(EnterpriseMapper enterpriseMapper) {
        super(enterpriseMapper);
        this.enterpriseMapper = enterpriseMapper;
    }

    /**
     * 查询企业详情
     * @return
     */
    public GetEnterpriseRes getEnterprise(GetEnterpriseReq getEnterpriseReq){
        if(getEnterpriseReq.getId().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "企业编码不可为空");
        }
        //根据id查询企业的相关信息
        Enterprise enterpriseEntity = enterpriseMapper.selectByPrimaryKey(getEnterpriseReq.getId());
        if(enterpriseEntity != null) {
            try{
                GetEnterpriseRes getEnterpriseRes = new GetEnterpriseRes();
                getEnterpriseRes.setId(enterpriseEntity.getId());
                getEnterpriseRes.setName(enterpriseEntity.getName());
                getEnterpriseRes.setCode(enterpriseEntity.getCode());
                getEnterpriseRes.setParentId(enterpriseEntity.getParentId());
                getEnterpriseRes.setParentName("根目录");
                getEnterpriseRes.setAllowSize(enterpriseEntity.getAllowSize());
                getEnterpriseRes.setAllowUser(enterpriseEntity.getAllowUser());
                getEnterpriseRes.setMakeSize(enterpriseEntity.getMakeSize());
                getEnterpriseRes.setMakeUser(enterpriseEntity.getMakeUser());
                getEnterpriseRes.setValidate(enterpriseEntity.getValidate());
                getEnterpriseRes.setStatus(enterpriseEntity.getStatus());
                getEnterpriseRes.setLicense(enterpriseEntity.getLicense());
                getEnterpriseRes.setLicenseTerm(enterpriseEntity.getLicenseTerm());
                getEnterpriseRes.setCreated_time(enterpriseEntity.getCreatedTime());
                getEnterpriseRes.setSysName(enterpriseEntity.getSysName());
                getEnterpriseRes.setSysLogo(enterpriseEntity.getSysLogo());
                getEnterpriseRes.setProContent(enterpriseEntity.getProContent());
                getEnterpriseRes.setProMemo(enterpriseEntity.getProMemo());
                getEnterpriseRes.setProStatus(enterpriseEntity.getProStatus());
                switch(enterpriseEntity.getProStatus()) {
                    case 1:
                        getEnterpriseRes.setProStatusName("待审核");
                        break;
                    case 2:
                        getEnterpriseRes.setProStatusName("审核未通过");
                        break;
                    case 3:
                        getEnterpriseRes.setProStatusName("审核已通过");
                        break;
                }
                //设置邀请链接
                getEnterpriseRes.setInviteLink("http://zl.baoluaninfo.com/#/validateTel?dep="+enterpriseEntity.getCode());
                return getEnterpriseRes;
            }catch (Exception e){
                throw new BusinessException(ErrorCode.NULL_POINTER, "企业信息不存在");
            }
        }else{
            throw new BusinessException(ErrorCode.NULL_POINTER, "企业信息不存在");
        }
    }

    /**
     * 新增企业
     * @return
     */
    public void addEnterprise(AddEnterpriseReq addEnterpriseReq,HttpServletRequest request){
        //1:参数验证
        if(addEnterpriseReq.getName().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "企业名称不可为空");
        }
        if(addEnterpriseReq.getCode().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "企业编码不可为空");
        }
        if(addEnterpriseReq.getParentId().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "上级企业编码不可为空");
        }

        //2：判断企业名称是否已经存在
        Enterprise enterpriseEntity = enterpriseMapper.getEnterpriseByName(addEnterpriseReq.getName(), addEnterpriseReq.getParentId());
        if(enterpriseEntity != null){
            throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该名称已存在,请重新输入");
        }

        //3：判断企业名称是否已经存在
        enterpriseEntity = enterpriseMapper.SelectDepByEntCode(addEnterpriseReq.getCode());
        if(enterpriseEntity != null){
            throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该企业编码已存在,请重新输入");
        }

        //4：新增企业
        enterpriseEntity = new Enterprise();
        enterpriseEntity.setId(UuidUtil.getRandomUuid());
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        enterpriseEntity.setName(addEnterpriseReq.getName());
        enterpriseEntity.setCode(addEnterpriseReq.getCode());
        enterpriseEntity.setParentId(addEnterpriseReq.getParentId());
        enterpriseEntity.setStatus(Enterprise.Default_Pass);
        enterpriseEntity.setAllowSize(Enterprise.Default_allowSize);
        enterpriseEntity.setAllowUser(Enterprise.Default_allowUser);
        //计算在系统当前时间的基础上加一年，也就是延迟一年
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
        Date date=curr.getTime();
        //默认企业一年后过期
        enterpriseEntity.setValidate(date);
        enterpriseEntity.setStatus(Enterprise.Default_Pass);
        enterpriseEntity.setProStatus(Enterprise.Pro_Wait);
        //营业执照url地址
        enterpriseEntity.setLicense(addEnterpriseReq.getLicense());
        //营业执照有效期
        enterpriseEntity.setLicenseTerm(addEnterpriseReq.getLicenseTerm());
        enterpriseEntity.setSysName(addEnterpriseReq.getSysName());
        enterpriseEntity.setSysLogo(addEnterpriseReq.getSysLogo());
        //设置邀请链接
        enterpriseEntity.setInviteLink("http://zl.baoluaninfo.com/#/validateTel?dep="+addEnterpriseReq.getCode());
        enterpriseEntity.setCreateUserid(userId);
        enterpriseEntity.setCreatedTime(new Date());
        enterpriseEntity.setUpdateUserid(userId);
        enterpriseEntity.setUpdatedTime(new Date());
        //添加企业信息
        enterpriseMapper.insertSelective(enterpriseEntity);

        EnterpriseUser entUser = new EnterpriseUser();
        entUser.setUserId(userId);
        //在个人用户的信息基础上，添加一些新的数据进去
        entUser.setEid(enterpriseEntity.getId());
        entUser.setDepartId(enterpriseEntity.getId());
        entUser.setAllowSize(Enterprise.Default_allowSize);
        entUser.setRegStatus(1);
        entUser.setCreatedTime(new Date());
        entUser.setIntegral(0);
        entUser.setMakeSize(0L);
        entUser.setStatus(1);
        //添加审核通过的企业的企业用户记录
        enterpriseUserMapper.insertSelective(entUser);
        //保存信息到角色表(角色为企业管理员)
        this.insetRole(enterpriseEntity.getId(),UserPower.Ent_Admin,"企业管理员");
        //保存信息到角色表(角色为员工)
        this.insetRole(enterpriseEntity.getId(),UserPower.Ent_Staff,"员工");
        //保存用户信息到用户角色表
        UserPower userPower = new UserPower();
        //设置企业
        userPower.setEid(enterpriseEntity.getId());
        //设置该用户所属角色为企业管理员
        userPower.setRoleId(UserPower.Ent_Admin);
        userPower.setUserId(userId);
        //添加用户角色表记录
        userPowerMapper.insert(userPower);
    }

    /**
     * 修改企业
     * @return
     */
    public void editEnterprise(EditEnterpriseReq editEnterpriseReq,HttpServletRequest request){
        try{
            if(editEnterpriseReq.getId().isEmpty()){
                throw new BusinessException(ErrorCode.NULL_POINTER, "企业编码不可为空");
            }
            //1:根据id查询企业相关信息
            Enterprise entEntity = enterpriseMapper.selectByPrimaryKey(editEnterpriseReq.getId());
            if(entEntity != null) {
                if(editEnterpriseReq.getType() ==1) {
                    //2:修改企业的类型为组织管理的修改企业
                    //2.1修改的企业名称不等于原来用的企业名称，查询企业名称是否已经存在
                    Enterprise enterpriseEntity = enterpriseMapper.getEnterpriseByName(editEnterpriseReq.getName(), editEnterpriseReq.getParentId());
                    if (enterpriseEntity != null  && enterpriseEntity.getId().equals(editEnterpriseReq.getId()) == false) {
                        throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该名称已存在,请重新输入");
                    }
                    //2.2：判断企业名称是否已经存在
                    enterpriseEntity = enterpriseMapper.SelectDepByEntCode(editEnterpriseReq.getCode());
                    if(enterpriseEntity != null && enterpriseEntity.getId().equals(editEnterpriseReq.getId()) == false){
                        throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该企业编码已存在,请重新输入");
                    }
                    //2.3组织管理的修改企业
                    entEntity.setCode(editEnterpriseReq.getCode());
                    entEntity.setName(editEnterpriseReq.getName());
                    entEntity.setLicense(editEnterpriseReq.getLicense());
                    entEntity.setLicenseTerm(editEnterpriseReq.getLicenseTerm());
                    entEntity.setSysName(editEnterpriseReq.getSysName());
                    entEntity.setSysLogo(editEnterpriseReq.getSysLogo());
                    //设置邀请链接
                    entEntity.setInviteLink("http://zl.baoluaninfo.com/#/validateTel?dep="+editEnterpriseReq.getCode());
                }else{
                    //3:企业管理的修改企业，即超级管理员管理企业
                    entEntity.setAllowSize(editEnterpriseReq.getAllowSize());
                    entEntity.setAllowUser(editEnterpriseReq.getAllowUser());
                    entEntity.setValidate(editEnterpriseReq.getValidate());
                    entEntity.setStatus(editEnterpriseReq.getStatus());
                }
                //4:获取当前用户的userId
                HttpSession session = request.getSession();
                String userId = (String) session.getAttribute("userId");
                entEntity.setUpdateUserid(userId);
                entEntity.setUpdatedTime(new Date());
                enterpriseMapper.updateByPrimaryKeySelective(entEntity);
            }else{
                throw new BusinessException(ErrorCode.NULL_POINTER, "修改企业失败");
            }
        }catch (Exception e){
            throw new BusinessException(ErrorCode.NULL_POINTER, "修改企业失败");
        }
    }

    /**
     * 删除企业
     * @return
     */
    public void deleteEnterprise(DeleteEnterpriseReq deleteEnterpriseReq,HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String eId = (String)session.getAttribute("eId");
        //根据id查询相关信息
        Enterprise entEntity = enterpriseMapper.selectByPrimaryKey(deleteEnterpriseReq.getEId());
        if(entEntity != null){
            if(entEntity.getParentId().equals(eId)){
                throw new BusinessException(ErrorCode.NULL_POINTER, "您已登录该企业，不能删除！");
            }else {
                int userNumber = enterpriseUserMapper.selectCountUserByEid(deleteEnterpriseReq.getEId());
                if(userNumber>1){
                    throw new BusinessException(ErrorCode.NULL_POINTER, "该部门下尚有职员，不能删除");
                }
                int depNumber = departmentMapper.selectDepartByEid(deleteEnterpriseReq.getEId());
                if(depNumber > 0){
                    throw new BusinessException(ErrorCode.NULL_POINTER, "该部门尚有下级部门，不能删除");
                }
                try {
                    enterpriseMapper.deleteByPrimaryKey(deleteEnterpriseReq.getEId());
                }catch (Exception e){
                    throw new BusinessException(ErrorCode.NULL_POINTER, "删除部门失败");
                }
            }
        }else{
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门信息不存在");
        }
    }

    /**
     * 审核企业
     * @return
     */
    public void proEnterprise(ProEnterpriseReq proEnterpriseReq,HttpServletRequest request){
        try{
            Enterprise  entEntity = null;
            //获取当前用户的userId，即审核人
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            //根据id查询企业的相关信息
            entEntity = enterpriseMapper.selectByPrimaryKey(proEnterpriseReq.getEntId());
            entEntity.setProStatus(proEnterpriseReq.getProStatus());
            entEntity.setProTime(new Date());
            entEntity.setProContent(proEnterpriseReq.getProContent());
            entEntity.setProMemo(proEnterpriseReq.getProMemo());
            entEntity.setProUserid(userId);
            enterpriseMapper.updateByPrimaryKeySelective(entEntity);
            //如果审核状态为通过，则将创建人保存到企业用户表，并创建用户角色记录
        }catch (Exception e){
            throw new BusinessException(ErrorCode.NULL_POINTER, "审核企业失败");
        }
    }

    /**
     * 保存角色表信息   审核企业时调用
     * @return
     */
    public void insetRole(String eId,String id,String name){
        Role role = new Role();
        //设置为当前审核通过的企业
        role.setEid(eId);
        //设置该企业所属角色为企业管理员
        role.setId(id);
        role.setName(name);
        //添加用户角色表记录
        roleMapper.insertSelective(role);
    }

    /**
     * 查询企业列表
     * @return
     */
    public RspPage<GetEnterpriseListRes> getEnterpriseList(GetEnterpriseListReq getEnterpriseListReq){
        RspPage<GetEnterpriseListRes> rspPage =new RspPage<GetEnterpriseListRes>();
        Page page= PageHelper.startPage(getEnterpriseListReq.getPageNum(), getEnterpriseListReq.getPageSize(),true);
        //搜索查询的判断
        if(getEnterpriseListReq.getStatus() == 1 || getEnterpriseListReq.getStatus() == 2  ){
            //如果是选了待审核或者审核不通过
            switch(getEnterpriseListReq.getStatus()) {
                case 1:
                    getEnterpriseListReq.setProStatus(1);
                    break;
                case 2:
                    getEnterpriseListReq.setProStatus(2);
                    break;
            }
            getEnterpriseListReq.setStatus(-1);
        }else if(getEnterpriseListReq.getStatus() == 3 || getEnterpriseListReq.getStatus() == 4 ){
            getEnterpriseListReq.setProStatus(-1);
            //判断状态类型，如果为3，则代表为启用，为4则代表为禁用
            switch(getEnterpriseListReq.getStatus()) {
                case 3:
                    getEnterpriseListReq.setStatus(1);
                    break;
                case 4:
                    getEnterpriseListReq.setStatus(2);
                    break;
            }
        }
        List<GetEnterpriseListRes> entList=enterpriseMapper.selectAllEnterprise(getEnterpriseListReq);
        rspPage.setData_list(entList);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询我的企业列表
     * @return
     */
    public List<GetEnterpriseListRes> getMyEnterpriseList(HttpServletRequest request){
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        List<GetEnterpriseListRes> entList=enterpriseMapper.selectMyEnterprise(userId);
        return entList;
    }
}
