package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.entity.certificate.Certificate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */
public interface ICertificateService extends IBaseService<Certificate,String> {

    /**
     * 查询证书类型列表
     * @return
     */
    RspPage<GetCertificateListRes> getCertificateList(GetCertificateListReq getCertificateListReq, HttpServletRequest request);

    /**
     * 证书审核
     * @return
     */
    void proCertificate(ProCertificateReq ProCertificateReq, HttpServletRequest request);

    /**
     * 根据用户编号查询用户证书列表
     * @return
     */
    List<GetUserCertificateListRes> getCertificateListByUser(String userId, HttpServletRequest request);

    /**
     * 查询证书详情
     * @return
     */
    GetCertificateRes getCertificate(String id, HttpServletRequest request);
}
