package com.zl.school.business.dao.certificate;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.dto.certificate.GetCertificateListReq;
import com.zl.school.business.dto.certificate.GetCertificateListRes;
import com.zl.school.business.dto.certificate.GetUserCertificateListReq;
import com.zl.school.business.dto.certificate.GetUserCertificateListRes;
import com.zl.school.business.entity.certificate.Certificate;

import java.util.List;

/**
 * @author MrBird
 */
public interface CertificateMapper extends BaseDao<Certificate,String> {
    /**
     * 查询企业下的所有证书
     * @return
     */
    List<GetCertificateListRes> selectAllCerByEid(GetCertificateListReq getCerListReq);
    /**
     * 查询用户证书列表
     * @return
     */
    List<GetUserCertificateListRes> selectUserCertificateById(String eId,String userId);
}
