package com.zl.school.business.dao.exam;

import com.zl.school.business.dto.label.GetQuestionInfoListReq;
import com.zl.school.business.dto.label.GetQuestionInfoListRes;
import com.zl.school.business.dto.label.GetQuestionListRes;
import com.zl.school.business.dto.label.GetQuestionReq;
import com.zl.school.business.entity.exam.QuestionInfo;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface QuestionInfoMapper extends BaseDao<QuestionInfo,String> {

    /**
     * 查询企业下的所有题目
     * @return
     */
    List<GetQuestionInfoListRes> selectAllQuestionInfoByEid(GetQuestionInfoListReq req);

    /**
     * 根据标签id列表以及题目数量随机筛选出相应数量的单选题
     */
    List<QuestionInfo>  selectSingleList(String labelIds,int single,String eId,int type);

    /**
     * 根据标签查询题目列表
     * @return
     */
    List<GetQuestionListRes> selectQuestionListByLabel(GetQuestionReq getQuestionReq);
}
