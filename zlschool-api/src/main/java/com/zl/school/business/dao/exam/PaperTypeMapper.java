package com.zl.school.business.dao.exam;

import com.zl.school.business.dto.exam.GetPaperTypeListRes;
import com.zl.school.business.entity.exam.PaperType;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface PaperTypeMapper extends BaseDao<PaperType,String> {

    /**
     * 查询企业下的所有试卷类型
     * @return
     */
    List<GetPaperTypeListRes> selectAllPaperByEid(String eId);
}
