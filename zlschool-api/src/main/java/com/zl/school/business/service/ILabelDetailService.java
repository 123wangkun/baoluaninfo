package com.zl.school.business.service;

import com.zl.school.business.dto.label.GetCheckLabelTreeRes;
import com.zl.school.business.entity.label.LabelDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ILabelDetailService extends IBaseService<LabelDetail,String> {
    /**
     * 查询标签目录树勾选状态
     * @return
     */
    GetCheckLabelTreeRes getCheckLabelTree(String id, HttpServletRequest request);
}
