package com.zl.school.business.dao.system;

import com.zl.school.business.entity.system.Department;
import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.system.Enterprise;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface DepartmentMapper extends BaseDao<Department,String> {

    /**
     * 根据部门邀请码查询部门的相关信息
     *
     */
    Department selectDepByCode(String entCode);

    /**
     * 获取部门信息—部门名称
     *
     */
    Department getDepartmentByName(String name,String parentId);

    /**
     * 获取该部门的所有子级部门
     *
     */
    List<Department> selectChildAll(String depId);

    /**
     * 根据企业id统计该企业下的总部门数
     *
     * @return
     */
    int selectDepartByEid(String eId);

    /**
     * 根据企业id查询企业下的所有部门
     *
     */
    List<Department> selectAllDepartByEid(String eId);

}
