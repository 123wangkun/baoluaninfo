package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.entity.system.Enterprise;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */
public interface IEnterpriseService extends IBaseService<Enterprise,String> {

    /**
     * 查询企业详情
     * @return
     */
    GetEnterpriseRes getEnterprise(GetEnterpriseReq getEnterpriseReq);

    /**
     * 新增企业部门
     * @return
     */
    void addEnterprise(AddEnterpriseReq addEnterpriseReq,HttpServletRequest request);

    /**
     * 修改企业
     * @return
     */
    void editEnterprise(EditEnterpriseReq editEnterpriseReq,HttpServletRequest request);

    /**
     * 删除企业
     * @return
     */
    void deleteEnterprise(DeleteEnterpriseReq deleteEnterpriseReq,HttpServletRequest request);

    /**
     * 审核企业
     * @return
     */
    void proEnterprise(ProEnterpriseReq proEnterpriseReq,HttpServletRequest request);

    /**
     * 查询企业列表
     * @return
     */
    RspPage<GetEnterpriseListRes> getEnterpriseList(GetEnterpriseListReq getEnterpriseListReq);

    /**
     * 查询我的企业列表
     * @return
     */
    List<GetEnterpriseListRes> getMyEnterpriseList(HttpServletRequest request);


}
