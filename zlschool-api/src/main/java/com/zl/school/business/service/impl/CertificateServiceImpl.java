package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.dao.certificate.CertificateMapper;
import com.zl.school.business.dao.certificate.CertificateTypeMapper;
import com.zl.school.business.dao.system.EnterpriseUserMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.entity.certificate.Certificate;
import com.zl.school.business.entity.certificate.CertificateType;
import com.zl.school.business.entity.system.EnterpriseUser;
import com.zl.school.business.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author MrBird
 */
@Service
public class CertificateServiceImpl extends AbstractServiceImpl<Certificate,String> implements ICertificateService {

    @Autowired
    private CertificateMapper mapper;
    @Autowired
    private CertificateTypeMapper typeMapper;
    @Autowired
    private EnterpriseUserMapper entUserMapper;

    public CertificateServiceImpl(CertificateMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询证书列表
     * @return
     */
    @Override
    public RspPage<GetCertificateListRes> getCertificateList(GetCertificateListReq getCerListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getCerListReq.setEId(eId);
        RspPage<GetCertificateListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCerListReq.getPageNum(), getCerListReq.getPageSize(), true);
        //搜索查询的判断
        if(getCerListReq.getState() == 1 || getCerListReq.getState() == 2  ){
            //如果是选了待审核或者审核不通过
            switch(getCerListReq.getState()) {
                case 1:
                    getCerListReq.setProStatus(1);
                    break;
                case 2:
                    getCerListReq.setProStatus(2);
                    break;
            }
            getCerListReq.setState(-1);
        }else if(getCerListReq.getState() == 3 || getCerListReq.getState() == 4 ){
            getCerListReq.setProStatus(-1);
            //判断状态类型，3，代表为启用，为4则代表为禁用
            switch(getCerListReq.getState()) {
                case 3:
                    getCerListReq.setState(1);
                    break;
                case 4:
                    getCerListReq.setState(2);
                    break;
            }
        }
        List<GetCertificateListRes> orders = mapper.selectAllCerByEid(getCerListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 证书审核
     * @return
     */
    @Override
    public void proCertificate(ProCertificateReq ProCerReq, HttpServletRequest request){
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        Certificate certificate = null;
        certificate = mapper.selectByPrimaryKey(ProCerReq.getId());
        if(certificate != null){
            certificate.setProContent(ProCerReq.getProContent());
            certificate.setProdTime(new Date());
            certificate.setProMemo(ProCerReq.getProMemo());
            certificate.setProUser(userId);
            certificate.setProStatus(ProCerReq.getProStatus());
            mapper.updateByPrimaryKeySelective(certificate);
            //判断审核是否为通过
            if(ProCerReq.getProStatus()==3) {
                //根据证书id查询证书基本信息
                CertificateType cerType = typeMapper.selectByKey(certificate.getEid(),certificate.getTypeId());
                //根据企业id以及用户id查询企业用户信息
                EnterpriseUser entUser = entUserMapper.selectEntUser(certificate.getEid(),certificate.getUserId());
                if(cerType!=null && entUser!=null) {
                    //重新将获得的积分累加到企业用户的总积分当中
                    int total = entUser.getIntegral() + cerType.getIntegral();
                    entUser.setIntegral(total);
                    //更新用户积分总数
                    entUserMapper.updateByPrimaryKeySelective(entUser);
                }
            }
        }
    }

    /**
     * 根据用户编号查询用户证书列表
     * @return
     */
    @Override
    public List<GetUserCertificateListRes> getCertificateListByUser(String userId, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //根据企业id以及用户id查询用户证书列表
        List<GetUserCertificateListRes> orders = mapper.selectUserCertificateById(eId,userId);
        return orders;
    }

    /**
     * 查询证书详情
     * @return
     */
    @Override
    public GetCertificateRes getCertificate(String id, HttpServletRequest request){
        GetCertificateRes certificateRes = null;
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        //根据证书id查询证书基本信息
//        certificateRes = mapper.selectByKey(eId,id);
        return null;
    }
}
