package com.zl.school.business.dao.exam;

import com.zl.school.business.entity.exam.QuestionOptions;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface QuestionOptionsMapper extends BaseDao<QuestionOptions,String> {

    /**
     * 根据题目id查询选项列表
     */
    List<QuestionOptions> selectByQuestionId(String questionId);

    /**
     * 根据题目id删除选项
     */
    void deleteByQuestion(String questionId);

    /**
     * 根据题目编号查询统计关联选项总记录数
     */
    int selectOptionsByQuestion(String questionId);
}
