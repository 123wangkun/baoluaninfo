package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.entity.check.CheckDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICheckDetailService extends IBaseService<CheckDetail,String> {

    /**
     * 查询考核详情列表
     * @return
     */
    RspPage<GetCheckDetailListRes> getCheckDetailList(GetCheckDetailListReq getCheckDetailListReq, HttpServletRequest request);

    /**
     * 查询考核详情详情
     * @return
     */
    GetCheckDetailRes getCheckDetail(String id, HttpServletRequest request);

    /**
     * 新增考核详情
     * @return
     */
    void addCheckDetail(AddCheckDetailReq addCheckDetailReq, HttpServletRequest request);

    /**
     * 修改考核详情
     * @return
     */
    void editCheckDetail(EditCheckDetailReq editCheckDetailReq, HttpServletRequest request);

    /**
     * 删除考核详情
     * @return
     */
    void deleteCheckDetail(String id, HttpServletRequest request);
    
}
