package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.entity.certificate.CertificateType;
import com.zl.school.business.dao.certificate.CertificateTypeMapper;
import com.zl.school.business.service.ICertificateTypeService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CertificateTypeServiceImpl extends AbstractServiceImpl<CertificateType,String> implements ICertificateTypeService {

    @Autowired
    private CertificateTypeMapper mapper;

    public CertificateTypeServiceImpl(CertificateTypeMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询证书类型列表
     * @return
     */
    @Override
    public RspPage<GetCertificateTypeListRes> getCertificateTypeList(GetCertificateTypeListReq getCerTypeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetCertificateTypeListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerTypeListReq.getPageNum(), getCerTypeListReq.getPageSize(), true);
        List<GetCertificateTypeListRes> orders = mapper.selectAllByEid(eId);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询证书详情
     * @return
     */
    @Override
    public GetCertificateTypeRes getCertificateType(String id, HttpServletRequest request){
        CertificateType cerType = null;
        GetCertificateTypeRes certificateTypeRes = null;
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //根据证书id查询证书基本信息
        cerType = mapper.selectByKey(eId,id);
        if(cerType != null){
            certificateTypeRes = new GetCertificateTypeRes();
            certificateTypeRes.setId(cerType.getId());
            certificateTypeRes.setIntegral(cerType.getIntegral());
            certificateTypeRes.setName(cerType.getName());
            certificateTypeRes.setState(cerType.getState());
            certificateTypeRes.setMemo(cerType.getMemo());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询证书详情失败！");
        }
        return certificateTypeRes;
    }

    /**
     * 新增证书类型
     * @return
     */
    @Override
    public void addCertificateType(AddCertificateTypeReq addCerTypeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        CertificateType cerType = new CertificateType();
        cerType.setId(UuidUtil.getRandomUuid());
        cerType.setEid(eId);
        cerType.setIntegral(addCerTypeReq.getIntegral());
        cerType.setName(addCerTypeReq.getName());
        cerType.setMemo(addCerTypeReq.getMemo());
        cerType.setState(addCerTypeReq.getState());
        cerType.setCreatedTime(new Date());
        cerType.setCreateUser(userId);
        mapper.insertSelective(cerType);
    }

    /**
     * 修改证书类型
     * @return
     */
    @Override
    public void editCertificateType(EditCertificateTypeReq editCerTypeReq, HttpServletRequest request){
        CertificateType cerType = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据证书id查询证书信息
        cerType = mapper.selectByKey(eId,editCerTypeReq.getId());
        if(cerType != null){
            cerType.setIntegral(editCerTypeReq.getIntegral());
            cerType.setName(editCerTypeReq.getName());
            cerType.setEid(eId);
            cerType.setMemo(editCerTypeReq.getMemo());
            cerType.setState(editCerTypeReq.getState());
            cerType.setUpdatedTime(new Date());
            cerType.setUpdateUser(userId);
            //更新证书类型信息
            mapper.updateByPrimaryKeySelective(cerType);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改证书信息失败！");
        }

    }

    /**
     * 删除证书类型
     * @return
     */
    @Override
    public void deleteCertificateType(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "证书类型编号不能为空！");
        }
        //获取当前用户的id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //统计关联该类型的证书的总数量
        int number = mapper.selectCountCertificateType(eId,id);
        if(number>0) {
            throw new BusinessException(ErrorCode.ERROR_PHONE, "该类型已有证书绑定，不能删除！");
        }else{
            //根据id删除证书类型
            mapper.deleteByKey(eId, id);
        }
    }
}
