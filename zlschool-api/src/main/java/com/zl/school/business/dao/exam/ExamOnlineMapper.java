package com.zl.school.business.dao.exam;

import com.zl.school.business.dto.exam.GetExamOnlineUserReq;
import com.zl.school.business.dto.exam.GetExamOnlineUserRes;
import com.zl.school.business.entity.exam.ExamOnline;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ExamOnlineMapper extends BaseDao<ExamOnline,String> {

    /**
     * 查询统计参加该试卷考试的所有记录
     * @return
     */
    int selectCountExamOnline(String paperId);

    /**
     * 查询线上考试已考用户列表
     * @return
     */
    List<GetExamOnlineUserRes> selectExamOnlineUserList(GetExamOnlineUserReq req);

}
