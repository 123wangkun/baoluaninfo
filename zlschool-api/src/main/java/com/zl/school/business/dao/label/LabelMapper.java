package com.zl.school.business.dao.label;

import com.zl.school.business.entity.label.Label;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface LabelMapper extends BaseDao<Label,String> {

    /**
     * 查询企业下的所有标签
     * @return
     */
    List<Label> selectAllLabelByEid(Label label);

    /**
     * 根据父级id所有标签
     * @return
     */
    Label selectAllLabelByParentId(String eId);

    /**
     * 查询企业下的所有标签
     * @return
     */
    List<Label> selectAllLabel(String eId);

    /**
     * 根据课程id查出标签列表
     * @return
     */
    List<Label> selectLabelById(String courseId);

    /**
     * 查询该标签是否有子级标签
     * @return
     */
    int selectCountChildLabel(String labelId);

}
