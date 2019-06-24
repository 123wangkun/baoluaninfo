package com.zl.school.business.dao.exam;

import com.zl.school.business.dto.exam.GetExamUnderListReq;
import com.zl.school.business.dto.exam.GetExamUnderListRes;
import com.zl.school.business.dto.exam.GetExamUnderUserReq;
import com.zl.school.business.dto.exam.GetExamUnderUserRes;
import com.zl.school.business.entity.exam.ExamUnder;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ExamUnderMapper extends BaseDao<ExamUnder,String> {

    /**
     * 根据企业查询所有的线下考试
     * @return
     */
    List<GetExamUnderListRes> selectAllExamUnderByEid(GetExamUnderListReq getCerTypeListReq);

    /**
     * 根据线下考试编号查询已考用户列表
     * @return
     */
    List<GetExamUnderUserRes> selectExamUnderUserList(GetExamUnderUserReq examUnderUserReq);

}
