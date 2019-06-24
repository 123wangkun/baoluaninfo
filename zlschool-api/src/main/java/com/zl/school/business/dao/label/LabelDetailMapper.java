package com.zl.school.business.dao.label;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.label.LabelDetail;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface LabelDetailMapper extends BaseDao<LabelDetail,String> {
    /**
     * 统计关联某类型的总数量
     * @return
     */
    int isExistByLabelDetail(String typeId);

    /**
     * 根据课程类型id删除记录
     * @return
     */
    void deleteByRelationId(String relationId);

    /**
     * 根据课程类型id查询所有记录
     * @return
     */
    List<LabelDetail> selectByRelationId(String relationId);
}
