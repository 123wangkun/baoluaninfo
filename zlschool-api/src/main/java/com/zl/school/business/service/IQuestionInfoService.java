package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.entity.exam.QuestionInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IQuestionInfoService extends IBaseService<QuestionInfo,String> {


    /**
     * 查询题目列表
     * @return
     */
    RspPage<GetQuestionInfoListRes> getQuestionInfoList(GetQuestionInfoListReq getQuestionInfoListReq, HttpServletRequest request);

    /**
     * 查询题目详情
     * @return
     */
    GetQuestionInfoRes getQuestionInfo(String id, HttpServletRequest request);

    /**
     * 新增题目
     * @return
     */
    void addQuestionInfo(AddQuestionInfoReq addQuestionInfoReq, HttpServletRequest request);

    /**
     * 修改题目
     * @return
     */
    void editQuestionInfo(EditQuestionInfoReq editQuestionInfoReq, HttpServletRequest request);

    /**
     * 删除题目
     * @return
     */
    void deleteQuestionInfo(String id, HttpServletRequest request);

    /**
     * 根据标签查询题目列表
     * @return
     */
    RspPage<GetQuestionListRes> getQuestionListByLabel(GetQuestionReq getQuestionReq, HttpServletRequest request);

}
