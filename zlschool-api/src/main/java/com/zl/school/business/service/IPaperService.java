package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.entity.exam.Paper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IPaperService extends IBaseService<Paper,String> {

    /**
     * 查询试卷列表
     * @return
     */
    RspPage<GetPaperListRes> getPaperList(GetPaperListReq getPaperListReq, HttpServletRequest request);

    /**
     * 查询试卷详情
     * @return
     */
    GetPaperRes getPaper(String id, HttpServletRequest request);

    /**
     * 新增试卷
     * @return
     */
    void addPaper(AddPaperReq addPaperReq, HttpServletRequest request);

    /**
     * 自动生成试卷
     * @return
     */
    void autoCreatePaper(AutoCreatePaperReq autoCreatePaperReq, HttpServletRequest request);

    /**
     * 修改试卷
     * @return
     */
    void editPaper(EditPaperReq editPaperReq, HttpServletRequest request);

    /**
     * 自动生成--修改试卷信息
     * @return
     */
    void editAutoPaper(EditAutoPaperReq editPaperReq, HttpServletRequest request);

    /**
     * 删除试卷
     * @return
     */
    void deletePaper(String id, HttpServletRequest request);
    
}
