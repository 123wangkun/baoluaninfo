package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.label.LabelDetailMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.label.*;
import com.zl.school.business.entity.label.Label;
import com.zl.school.business.dao.label.LabelMapper;
import com.zl.school.business.service.ILabelService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zl.school.business.dto.label.GetLabelTreeRes.LabelTree;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class LabelServiceImpl extends AbstractServiceImpl<Label,String> implements ILabelService {

    @Autowired
    private LabelMapper mapper;
    @Autowired
    private LabelDetailMapper labelDetailMapper;
    public LabelServiceImpl(LabelMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询标签列表
     * @return
     */
    @Override
    public RspPage<GetLabelListRes> getLabelList(GetLabelListReq getLabelListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetLabelListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getLabelListReq.getPageNum(), getLabelListReq.getPageSize(), true);
        List<GetLabelListRes> labelList =  new ArrayList();
        Label label = new Label();
        label.setEid(eId);
        label.setParentId(getLabelListReq.getParentId());
        label.setName(getLabelListReq.getName());
        //根据标签类型以及企业id查询所有标签列表
        List<Label> orders = mapper.selectAllLabelByEid(label);
        if(orders.size()>0) {
            for (int i = 0; i < orders.size(); i++) {
                GetLabelListRes labelListRes = new GetLabelListRes();
                labelListRes.setId(orders.get(i).getId());
                labelListRes.setName(orders.get(i).getName());
                labelListRes.setCreatedTime(orders.get(i).getCreatedTime());
                labelListRes.setMemo(orders.get(i).getMemo());
                if (orders.get(i).getParentId().equals("root")) {
                    labelListRes.setParentName("根标签");
                } else {
                    //根据父级id查询父级对象
                    Label orde = mapper.selectAllLabelByParentId(orders.get(i).getParentId());
                    labelListRes.setParentName(orde.getName());
                }
                //把对象添加进List
                labelList.add(labelListRes);
            }
        }
        rspPage.setData_list(labelList);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询标签目录树
     * @return
     */
    @Override
    public GetLabelTreeRes getLabelTree(HttpServletRequest request){
        GetLabelTreeRes labelTreeRes = new GetLabelTreeRes();
        //获取当前用户的所属企业
        HttpSession session = request.getSession();
        String eId = (String)session.getAttribute("eId");
        //查询该企业的所有记录
        List<Label> entList = mapper.selectAllLabel(eId);
        List<LabelTree> TreeList = new ArrayList();
        //循环企业记录
        for (Label labValue : entList) {
            LabelTree labTreeRes = new LabelTree();
            labTreeRes.setId(labValue.getId());
            labTreeRes.setName(labValue.getName());
            labTreeRes.setParentId(labValue.getParentId());
            TreeList.add(labTreeRes);
        }
        // 4:转换为树状结构
        List menuTree = this.getEntTreeByList(TreeList);
        labelTreeRes.setLabelTrees(menuTree);
        return labelTreeRes;
    }

    /**
     * 列表结构转换成树结构
     */
    public List getEntTreeByList(List<LabelTree> labTrees) {

        List<LabelTree> treeEntList = new ArrayList<LabelTree>();

        for (LabelTree pValue : labTrees) {
            if (pValue.getParentId().equals("root")) {
                treeEntList.add(pValue);
            }
            for (LabelTree entValue : labTrees) {
                if (entValue.getParentId().equals(pValue.getId())) {
                    if (pValue.getChildren() == null) {
                        List<LabelTree> childList = new ArrayList<LabelTree>();
                        childList.add(entValue);
                        pValue.setChildren(childList);
                    } else {
                        pValue.getChildren().add(entValue);
                    }
                }
            }
        }
        return treeEntList;
    }

    /**
     * 进入修改标签界面
     * @return
     */
    @Override
    public GetLabelRes getLabel(String id, HttpServletRequest request){
        Label label = null;
        GetLabelRes labelRes = null;
        //根据标签id查询标签基本信息
        label = mapper.selectByPrimaryKey(id);
        if(label != null){
            labelRes = new GetLabelRes();
            if(label.getParentId().equals("root")){
                labelRes.setParentName("根标签");
            }else{
                //根据父级id查询标签基本信息
                label = mapper.selectByPrimaryKey(label.getParentId());
                labelRes.setParentName(label.getName());
            }
            labelRes.setId(label.getId());
            labelRes.setName(label.getName());
            labelRes.setParentId(label.getParentId());
            labelRes.setMemo(label.getMemo());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询标签详情失败！");
        }
        return labelRes;
    }

    /**
     * 新增标签
     * @return
     */
    @Override
    public void addLabel(AddLabelReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Label label = new Label();
        label.setId(UuidUtil.getRandomUuid());
        label.setEid(eId);
        label.setParentId(addCerTypeReq.getParentId());
        label.setName(addCerTypeReq.getName());
        label.setMemo(addCerTypeReq.getMemo());
        label.setCreatedTime(new Date());
        label.setCreateUser(userId);
        mapper.insertSelective(label);
    }

    /**
     * 修改标签
     * @return
     */
    @Override
    public void editLabel(EditLabelReq editCerTypeReq, HttpServletRequest request){
        Label label = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据标签id查询标签信息
        label = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(label != null){
            label.setParentId(editCerTypeReq.getParentId());
            label.setName(editCerTypeReq.getName());
            label.setMemo(editCerTypeReq.getMemo());
            label.setUpdatedTime(new Date());
            label.setUpdateUser(userId);
            //更新标签信息
            mapper.updateByPrimaryKeySelective(label);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改标签信息失败！");
        }

    }

    /**
     * 删除标签
     * @return
     */
    @Override
    public void deleteLabel(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "标签编号不能为空！");
        }
        int labelDetail = labelDetailMapper.isExistByLabelDetail(id);
        if(labelDetail > 0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该标签已被使用，不能删除！");
        }
        //查询该标签是否有子级标签
        int childsize = mapper.selectCountChildLabel(id);
        if(childsize > 0){
            throw new BusinessException(ErrorCode.NULL_POINTER, "该标签尚有下级标签，不能删除");
        }
        //根据id删除标签
        mapper.deleteByPrimaryKey(id);
    }
}
