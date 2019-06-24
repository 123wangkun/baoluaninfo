package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.system.DeleteUserReq;
import com.zl.school.business.entity.system.EnterpriseUser;

public interface EnterpriseUserMapper extends BaseDao<EnterpriseUser,String> {

    /**
     * 根据企业id以及用户id查询企业用户表
     *
     * @return
     */
    EnterpriseUser selectEntUser(String eId, String userId);

    /**
     * 根据部门id统计该部门下的总人数
     *
     * @return
     */
    int selectCountUser(String depId);

    /**
     * 根据企业id统计该企业下的总人数
     *
     * @return
     */
    int selectCountUserByEid(String eId);

    /**
     * 删除企业用户
     *
     * @return
     */
    void deleteEntUser(EnterpriseUser entUser);

}
