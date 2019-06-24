package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.GetExamOnlineUserReq;
import com.zl.school.business.dto.exam.GetExamOnlineUserRes;
import com.zl.school.business.entity.exam.ExamOnline;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IExamOnlineService extends IBaseService<ExamOnline,String> {

    /**
     * 查询线上考试已考用户列表
     * @return
     */
    RspPage<GetExamOnlineUserRes> getExamOnlineUser(GetExamOnlineUserReq req, HttpServletRequest request);

}
