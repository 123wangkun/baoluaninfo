package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.entity.check.CheckInfo;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICheckInfoService extends IBaseService<CheckInfo,String> {
    /**
     * 查询考核列表
     * @return
     */
    RspPage<GetCheckInfoListRes> getCheckInfoList(GetCheckInfoListReq getCheckInfoListReq, HttpServletRequest request);

    /**
     * 查询考核详情
     * @return
     */
    GetCheckInfoRes getCheckInfo(String id, HttpServletRequest request);

    /**
     * 新增考核
     * @return
     */
    void addCheckInfo(AddCheckInfoReq addCheckInfoReq, HttpServletRequest request);

    /**
     * 修改考核
     * @return
     */
    void editCheckInfo(EditCheckInfoReq editCheckInfoReq, HttpServletRequest request);

    /**
     * 删除考核
     * @return
     */
    void deleteCheckInfo(String id, HttpServletRequest request);

}
