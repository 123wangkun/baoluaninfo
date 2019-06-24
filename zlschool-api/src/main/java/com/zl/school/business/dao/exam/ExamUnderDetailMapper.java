package com.zl.school.business.dao.exam;

import com.zl.school.business.entity.exam.ExamUnderDetail;
import com.zl.school.business.dao.BaseDao;
/**
 * @author 南京深卡网络技术有限公司
 */
public interface ExamUnderDetailMapper extends BaseDao<ExamUnderDetail,String> {

    /**
     * 根据线下考试id查询统计所有的记录总数
     * @return
     */
    int selectCountExamUnder(String underId);
}
