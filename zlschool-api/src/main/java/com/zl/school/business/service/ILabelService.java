package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.entity.label.Label;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ILabelService extends IBaseService<Label,String> {

    /**
     * 查询标签列表
     * @return
     */
    RspPage<GetLabelListRes> getLabelList(GetLabelListReq getLabelListReq, HttpServletRequest request);

    /**
     * 查询标签目录树
     * @return
     */
    GetLabelTreeRes getLabelTree(HttpServletRequest request);

    /**
     * 查询标签详情
     * @return
     */
    GetLabelRes getLabel(String id, HttpServletRequest request);

    /**
     * 新增标签
     * @return
     */
    void addLabel(AddLabelReq addLabelReq, HttpServletRequest request);

    /**
     * 修改标签
     * @return
     */
    void editLabel(EditLabelReq editLabelReq, HttpServletRequest request);

    /**
     * 删除标签
     * @return
     */
    void deleteLabel(String id, HttpServletRequest request);


}
