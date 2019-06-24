package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.*;
import com.zl.school.business.entity.exam.ExamUnder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IExamUnderService extends IBaseService<ExamUnder,String> {

    /**
     * 查询线下考试列表
     * @return
     */
    RspPage<GetExamUnderListRes> getExamUnderList(GetExamUnderListReq getExamUnderListReq, HttpServletRequest request);

    /**
     * 查询线下考试详情
     * @return
     */
    GetExamUnderRes getExamUnder(String id, HttpServletRequest request);

    /**
     * 新增线下考试
     * @return
     */
    void addExamUnder(AddExamUnderReq addExamUnderReq, HttpServletRequest request);

    /**
     * 修改线下考试
     * @return
     */
    void editExamUnder(EditExamUnderReq editExamUnderReq, HttpServletRequest request);

    /**
     * 删除线下考试
     * @return
     */
    void deleteExamUnder(String id, HttpServletRequest request);

    /**
     * 查询线下考试已考用户列表
     * @return
     */
    RspPage<GetExamUnderUserRes> getExamUnderUser(GetExamUnderUserReq req, HttpServletRequest request);
    
}
