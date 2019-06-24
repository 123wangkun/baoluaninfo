package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.entity.certificate.CertificateType;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface ICertificateTypeService extends IBaseService<CertificateType,String> {

    /**
     * 查询证书类型列表
     * @return
     */
    RspPage<GetCertificateTypeListRes> getCertificateTypeList(GetCertificateTypeListReq getCertificateTypeListReq, HttpServletRequest request);

    /**
     * 查询证书类型详情
     * @return
     */
    GetCertificateTypeRes getCertificateType(String id, HttpServletRequest request);

    /**
     * 新增证书类型
     * @return
     */
    void addCertificateType(AddCertificateTypeReq addCertificateTypeReq, HttpServletRequest request);

    /**
     * 修改证书类型
     * @return
     */
    void editCertificateType(EditCertificateTypeReq editCertificateTypeReq, HttpServletRequest request);

    /**
     * 删除证书类型
     * @return
     */
    void deleteCertificateType(String id, HttpServletRequest request);
}
