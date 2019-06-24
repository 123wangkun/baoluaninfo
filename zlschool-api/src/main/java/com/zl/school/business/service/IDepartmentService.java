package com.zl.school.business.service;

import com.zl.school.business.dto.system.AddDepartmentReq;
import com.zl.school.business.dto.system.EditDepartmentReq;
import com.zl.school.business.dto.system.GetDepartmentRes;
import com.zl.school.business.dto.system.GetDepartmentTreeRes;
import com.zl.school.business.entity.system.Department;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IDepartmentService extends IBaseService<Department,String> {

    /**
     * 查询企业目录树
     * @return
     */
    GetDepartmentTreeRes getDepartmentTree(HttpServletRequest request);
    
    /**
     * 查询部门详情
     * @return
     */
    GetDepartmentRes getDepartment(String depId,HttpServletRequest request);

    /**
     * 新增部门
     * @return
     */
    void addDepartment(AddDepartmentReq addDepartmentReq, HttpServletRequest request);

    /**
     * 修改部门
     * @return
     */
    void editDepartment(EditDepartmentReq editDepartmentReq, HttpServletRequest request);

    /**
     * 删除部门
     * @return
     */
    void deleteDepartment(String depId,HttpServletRequest request);

}
