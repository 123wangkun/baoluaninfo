package com.zl.school.business.dao.exam;

import com.zl.school.business.dto.exam.GetPaperListReq;
import com.zl.school.business.dto.exam.GetPaperListRes;
import com.zl.school.business.entity.exam.Paper;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface PaperMapper extends BaseDao<Paper,String> {

    /**
     * 根据类型id查询统计关联了该类型的所有试卷数量
     * @return
     */
    int selectPaperByType(String typeId);

    /**
     * 查询试卷列表
     * @return
     */
    List<GetPaperListRes> selectAllPaperByEid(GetPaperListReq getCerListReq);

}
