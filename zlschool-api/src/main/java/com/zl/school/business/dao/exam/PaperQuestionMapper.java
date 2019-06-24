package com.zl.school.business.dao.exam;

import com.zl.school.business.entity.exam.PaperQuestion;
import com.zl.school.business.dao.BaseDao;
/**
 * @author 南京深卡网络技术有限公司
 */
public interface PaperQuestionMapper extends BaseDao<PaperQuestion,String> {

    /**
     * 根据题目编号查询统计关联试卷总记录数
     */
    int selectCountByQuestion(String questionId);

    /**
     * 根据试卷编号删除试卷旧题目
     */
    void deleteByPaperId(String paperId);
}
