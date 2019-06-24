package com.zl.school.business.service.impl;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.dto.label.GetCheckLabelTreeRes;
import com.zl.school.business.dto.label.GetCheckLabelTreeRes.CheckLabelTree;
import com.zl.school.business.dto.label.GetLabelTreeRes;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.entity.label.LabelDetail;
import com.zl.school.business.service.ILabelDetailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class LabelDetailServiceImpl extends AbstractServiceImpl<LabelDetail,String> implements ILabelDetailService {

    @Autowired
    private LabelDetailMapper mapper;
    @Autowired
    private LabelMapper labelMapper;

    public LabelDetailServiceImpl(LabelDetailMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询标签目录树勾选状态
     * @return
     */
    @Override
    public GetCheckLabelTreeRes getCheckLabelTree(String id, HttpServletRequest request){
        GetCheckLabelTreeRes labelTreeRes = new GetCheckLabelTreeRes();
        //获取当前用户的所属企业
        HttpSession session = request.getSession();
        String eId = (String)session.getAttribute("eid");
        //查询该企业的所有标签
        List<Label> labList = labelMapper.selectAllLabel(eId);
        //查询该id已关联的所有记录
        List<LabelDetail> labelDetails = mapper.selectByRelationId(id);
        List<CheckLabelTree> TreeList = new ArrayList();
        //循环企业记录
        for (Label labValue : labList) {
            CheckLabelTree labTreeRes = new CheckLabelTree();
            labTreeRes.setId(labValue.getId());
            labTreeRes.setName(labValue.getName());
            labTreeRes.setParentId(labValue.getParentId());
            for (LabelDetail labDetailValue : labelDetails) {
                //如果该角色拥有的菜单id等于所有菜单列表的id，将勾选状态显示为TRUE
                if (labDetailValue.getLableId().equals(labValue.getId())) {
                    labTreeRes.setChecked(true);
                }
            }
            TreeList.add(labTreeRes);
        }
        // 4:转换为树状结构
        List menuTree = this.getEntTreeByList(TreeList);
        labelTreeRes.setLabelTree(menuTree);
        return labelTreeRes;
    }

    /**
     * 列表结构转换成树结构
     */
    public List getEntTreeByList(List<CheckLabelTree> labTrees) {

        List<CheckLabelTree> treeLabList = new ArrayList<CheckLabelTree>();

        for (CheckLabelTree pValue : labTrees) {
            if (pValue.getParentId().equals("root")) {
                treeLabList.add(pValue);
            }
            for (CheckLabelTree labValue : labTrees) {
                if (labValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<CheckLabelTree> childList = new ArrayList<CheckLabelTree>();
                        childList.add(labValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(labValue);
                    }
                }
            }
        }
        return treeLabList;
    }
}
