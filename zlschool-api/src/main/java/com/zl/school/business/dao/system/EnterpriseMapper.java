package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.system.GetEnterpriseListReq;
import com.zl.school.business.dto.system.GetEnterpriseListRes;
import com.zl.school.business.entity.system.Enterprise;

import java.util.List;

public interface EnterpriseMapper extends BaseDao<Enterprise,String> {
    /**
     * 获取企业信息—企业名称
     *
     * @return
     */
    Enterprise getEnterpriseByName(String name,String parentId);

    /**
     * 根据部门邀请码查询部门的相关信息
     *
     * @return
     */
    Enterprise SelectDepByEntCode(String entCode);

//    /**
//     * 根据parentId查询出对应企业的信息
//     *
//     * @return
//     */
//    Enterprise SelectComByParentId(String parentId);

//    /**
//     * 获取企业表所有记录
//     *
//     * @return
//     */
//    List<Enterprise> selectAll();

    /**
     * 查询企业列表
     * @return
     */
    List<GetEnterpriseListRes> selectAllEnterprise(GetEnterpriseListReq getEnterpriseListReq);

    /**
     * 查询我的企业列表
     * @return
     */
    List<GetEnterpriseListRes> selectMyEnterprise(String userId);
}
