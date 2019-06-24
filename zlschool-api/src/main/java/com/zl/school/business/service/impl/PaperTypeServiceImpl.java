package com.zl.school.business.service.impl;

import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.exam.PaperMapper;
import com.zl.school.business.dto.exam.AddPaperTypeReq;
import com.zl.school.business.dto.exam.EditPaperTypeReq;
import com.zl.school.business.dto.exam.GetPaperTypeListRes;
import com.zl.school.business.dto.exam.GetPaperTypeRes;
import com.zl.school.business.entity.exam.PaperType;
import com.zl.school.business.dao.exam.PaperTypeMapper;
import com.zl.school.business.service.IPaperTypeService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class PaperTypeServiceImpl extends AbstractServiceImpl<PaperType,String> implements IPaperTypeService {

    @Autowired
    private PaperTypeMapper mapper;
    @Autowired
    private PaperMapper paperMapper;

    public PaperTypeServiceImpl(PaperTypeMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }


    /**
     * 查询试卷类型列表
     * @return
     */
    @Override
    public List<GetPaperTypeListRes> getPaperTypeList(HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        List<GetPaperTypeListRes> orders = mapper.selectAllPaperByEid(eId);
        return orders;
    }

    /**
     * 查询试卷详情
     * @return
     */
    @Override
    public GetPaperTypeRes getPaperType(String id, HttpServletRequest request){
        PaperType paperType = null;
        GetPaperTypeRes paperTypeRes = null;
        //根据试卷id查询试卷基本信息
        paperType = mapper.selectByPrimaryKey(id);
        if(paperType != null){
            paperTypeRes = new GetPaperTypeRes();
            paperTypeRes.setId(paperType.getId());
            paperTypeRes.setMemo(paperType.getMemo());
            paperTypeRes.setName(paperType.getName());
            paperTypeRes.setPosition(paperType.getPosition());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询试卷详情失败！");
        }
        return paperTypeRes;
    }

    /**
     * 新增试卷类型
     * @return
     */
    @Override
    public void addPaperType(AddPaperTypeReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        PaperType paperType = new PaperType();
        paperType.setId(UuidUtil.getRandomUuid());
        paperType.setEid(eId);
        paperType.setPosition(paperType.getPosition());
        paperType.setName(addCerTypeReq.getName());
        paperType.setMemo(addCerTypeReq.getMemo());
        paperType.setCreatedTime(new Date());
        paperType.setCreateUser(userId);
        //将数据添加到数据表
        mapper.insertSelective(paperType);
    }

    /**
     * 修改试卷类型
     * @return
     */
    @Override
    public void editPaperType(EditPaperTypeReq editCerTypeReq, HttpServletRequest request){
        PaperType paperType = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据试卷id查询试卷信息
        paperType = mapper.selectByPrimaryKey(editCerTypeReq.getId());
        if(paperType != null){
            paperType.setName(editCerTypeReq.getName());
            paperType.setMemo(editCerTypeReq.getMemo());
            paperType.setPosition(editCerTypeReq.getPosition());
            paperType.setUpdatedTime(new Date());
            paperType.setUpdateUser(userId);
            //更新试卷类型信息
            mapper.updateByPrimaryKeySelective(paperType);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改试卷信息失败！");
        }

    }

    /**
     * 删除试卷类型
     * @return
     */
    @Override
    public void deletePaperType(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "试卷类型编号不能为空！");
        }
        //根据类型id查询该试卷类型是否已关联试卷
        int size = paperMapper.selectPaperByType(id);
        if(size>0){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "已有试卷绑定该类型，不能删除！");
        }else {
            //根据id删除试卷类型
            mapper.deleteByPrimaryKey(id);
        }
    }
}
