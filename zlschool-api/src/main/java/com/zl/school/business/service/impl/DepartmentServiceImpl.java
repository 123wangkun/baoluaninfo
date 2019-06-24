package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.system.EnterpriseMapper;
import com.zl.school.business.dao.system.EnterpriseUserMapper;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.dto.system.GetDepartmentTreeRes.DepartmentTree;
import com.zl.school.business.entity.system.Department;
import com.zl.school.business.dao.system.DepartmentMapper;
import com.zl.school.business.entity.system.Enterprise;
import com.zl.school.business.service.IDepartmentService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class DepartmentServiceImpl extends AbstractServiceImpl<Department,String> implements IDepartmentService {

    @Autowired
    private DepartmentMapper mapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public DepartmentServiceImpl(DepartmentMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询企业目录树
     * @return
     */
    public GetDepartmentTreeRes getDepartmentTree(HttpServletRequest request){
        // 1:获取当前用户的所属企业
        HttpSession session = request.getSession();
        String eId = (String)session.getAttribute("eId");
        //根据id查询企业的相关信息
        Enterprise enterpriseEntity = enterpriseMapper.selectByPrimaryKey(eId);
        //把查询出来的企业信息保存到部门目录树列表中
        DepartmentTree enterprise = new DepartmentTree();
        enterprise.setId(enterpriseEntity.getId());
        enterprise.setCode(enterpriseEntity.getCode());
        enterprise.setName(enterpriseEntity.getName());
        enterprise.setParentId(enterpriseEntity.getParentId());
        enterprise.setInviteLink(enterpriseEntity.getInviteLink());
        // 2:查询该企业下的所有部门数据
        List<Department> allList = mapper.selectAllDepartByEid(eId);

        // 3:循环数据
        List<DepartmentTree> TreeList = new ArrayList();
        for(Department dep : allList){
            DepartmentTree depTree = new DepartmentTree();
            depTree.setId(dep.getId());
            depTree.setName(dep.getName());
            depTree.setCode(dep.getCode());
            depTree.setParentId(dep.getParentId());
            depTree.setInviteLink(dep.getInviteLink());
            TreeList.add(depTree);
        }
        TreeList.add(enterprise);

        // 4:转换为树状结构
        List<DepartmentTree> menuTree = this.getDepTreeByList(TreeList);

        // 5:返回数据
        GetDepartmentTreeRes entTreeRes = new GetDepartmentTreeRes();
        entTreeRes.setDepTree(menuTree);
        return entTreeRes;
    }

    /**
     * 列表结构转换成树结构
     */
    public List getDepTreeByList(List<DepartmentTree> depTrees) {
        List<DepartmentTree> treeEntList = new ArrayList();

        for (DepartmentTree pValue : depTrees) {
            if (pValue.getParentId().equals("root")) {
                treeEntList.add(pValue);
            }
            for (DepartmentTree  entValue : depTrees) {
                if (entValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<DepartmentTree> childList = new ArrayList();
                        childList.add(entValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(entValue);
                    }
                }
            }
        }
        return treeEntList;
    }

    /**
     * 查询部门详情
     * @return
     */
    public GetDepartmentRes getDepartment(String depId,HttpServletRequest request){
        if(depId.isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "企业编码不可为空");
        }
        // 1:获取当前用户的所属企业
        HttpSession session = request.getSession();
        String eId = (String)session.getAttribute("eId");
        GetDepartmentRes getDepartmentRes = new GetDepartmentRes();
        //根据id查询部门的相关信息
        Department DepartmentEntity = mapper.selectByPrimaryKey(depId);
        if(DepartmentEntity != null) {
            try{
                getDepartmentRes.setId(DepartmentEntity.getId());
                getDepartmentRes.setName(DepartmentEntity.getName());
                getDepartmentRes.setCode(DepartmentEntity.getCode());
                getDepartmentRes.setParentId(DepartmentEntity.getParentId());
                getDepartmentRes.setInviteLink(DepartmentEntity.getInviteLink());
                if(DepartmentEntity.getParentId().equals(eId)){
                    Enterprise enterpriseEntity = enterpriseMapper.selectByPrimaryKey(eId);
                    if (enterpriseEntity != null) {
                        getDepartmentRes.setParentName(enterpriseEntity.getName());
                    }
                }else {
                    Department parentDepartment = mapper.selectByPrimaryKey(DepartmentEntity.getParentId());
                    if (parentDepartment != null) {
                        getDepartmentRes.setParentName(parentDepartment.getName());
                    }
                }
            }catch (Exception e){
                throw new BusinessException(ErrorCode.NULL_POINTER, "部门信息不存在");
            }
        }else{
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门信息不存在");
        }
        return getDepartmentRes;
    }

    /**
     * 新增部门
     * @return
     */
    public void addDepartment(AddDepartmentReq addDepartmentReq, HttpServletRequest request){
        //1:参数验证
        if(addDepartmentReq.getName().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门名称不可为空");
        }
        if(addDepartmentReq.getCode().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门编码不可为空");
        }
        if(addDepartmentReq.getParentId().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "上级部门编码不可为空");
        }
        //3：判断部门邀请码是否已经存在
        Department departmentEntity = mapper.selectDepByCode(addDepartmentReq.getCode());
        if(departmentEntity != null){
            throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该企业编码已存在,请重新输入");
        }

        //4：新增部门
        departmentEntity = new Department();
        departmentEntity.setId(UuidUtil.getRandomUuid());
        //获取当前用户的userId
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String eId = (String)session.getAttribute("eId");
        departmentEntity.setName(addDepartmentReq.getName());
        departmentEntity.setCode(addDepartmentReq.getCode());
        departmentEntity.setEid(eId);
        departmentEntity.setParentId(addDepartmentReq.getParentId());
        //设置邀请链接
        departmentEntity.setInviteLink("http://zl.baoluaninfo.com/#/validateTel?dep="+addDepartmentReq.getCode());
        departmentEntity.setCreateUserid(userId);
        departmentEntity.setCreatedTime(new Date());
        departmentEntity.setUpdateUserid(userId);
        departmentEntity.setUpdatedTime(new Date());
        //添加部门信息
        mapper.insertSelective(departmentEntity);
    }

    /**
     * 修改部门
     * @return
     */
    public void editDepartment(EditDepartmentReq editDepartmentReq, HttpServletRequest request){
        //1:参数验证
        if(editDepartmentReq.getName().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门名称不可为空");
        }
        if(editDepartmentReq.getCode().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门编码不可为空");
        }
        if(editDepartmentReq.getParentId().isEmpty()){
            throw new BusinessException(ErrorCode.NULL_POINTER, "上级部门编码不可为空");
        }

        //2：判断部门名称是否已经存在   同一父级id下不能存在相同名称的部门
        Department departmentEntity = mapper.getDepartmentByName(editDepartmentReq.getName(), editDepartmentReq.getParentId());
        if(departmentEntity != null && departmentEntity.getId().equals(editDepartmentReq.getDepId()) == false){
            throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该名称已存在,请重新输入");
        }

        //3：判断企业名称是否已经存在
        departmentEntity = mapper.selectDepByCode(editDepartmentReq.getCode());
        if(departmentEntity != null && departmentEntity.getId().equals(editDepartmentReq.getDepId()) == false){
            throw new BusinessException(ErrorCode.ADD_DATA_FAIL, "该部门邀请码已存在,请重新输入");
        }

        //根据id查询部门的相关信息
        departmentEntity = mapper.selectByPrimaryKey(editDepartmentReq.getDepId());
        if(departmentEntity != null) {
            try{
                //获取当前用户的userId
                HttpSession session = request.getSession();
                String userId = (String) session.getAttribute("userId");
                departmentEntity.setName(editDepartmentReq.getName());
                departmentEntity.setCode(editDepartmentReq.getCode());
                departmentEntity.setParentId(editDepartmentReq.getParentId());
                //设置邀请链接
                departmentEntity.setInviteLink("http://zl.baoluaninfo.com/#/validateTel?dep="+editDepartmentReq.getCode());
                departmentEntity.setUpdateUserid(userId);
                departmentEntity.setUpdatedTime(new Date());
                //修改部门信息
                mapper.updateByPrimaryKeySelective(departmentEntity);
            }catch (Exception e){
                throw new BusinessException(ErrorCode.NULL_POINTER, "修改部门失败");
            }
        }else{
            throw new BusinessException(ErrorCode.NULL_POINTER, "修改部门失败");
        }
    }

    /**
     * 删除部门
     * @return
     */
    public void deleteDepartment(String depId,HttpServletRequest request){
        //根据id查询相关信息
        Department depEntity = mapper.selectByPrimaryKey(depId);
        if(depEntity != null){
            int userNumber = enterpriseUserMapper.selectCountUser(depId);
            if(userNumber>0){
                throw new BusinessException(ErrorCode.NULL_POINTER, "该部门下尚有职员，不能删除");
            }
            List<Department> childList = mapper.selectChildAll(depId);
            if(childList.size() > 0){
                throw new BusinessException(ErrorCode.NULL_POINTER, "该部门尚有下级部门，不能删除");
            }
            try {
                //根据部门id删除部门信息
                mapper.deleteByPrimaryKey(depId);
            }catch (Exception e){
                throw new BusinessException(ErrorCode.NULL_POINTER, "删除部门失败");
            }
        }else{
            throw new BusinessException(ErrorCode.NULL_POINTER, "部门信息不存在");
        }
    }
}
