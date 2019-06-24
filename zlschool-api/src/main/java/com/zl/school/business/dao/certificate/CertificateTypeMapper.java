package com.zl.school.business.dao.certificate;

import com.zl.school.business.dto.certificate.GetCertificateTypeListRes;
import com.zl.school.business.entity.certificate.CertificateType;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CertificateTypeMapper extends BaseDao<CertificateType,String> {

    /**
     * 查询企业下的所有证书类型
     * @return
     */
    List<GetCertificateTypeListRes> selectAllByEid(String eId);
    /**
     * 根据企业id和证书id查询证书类型信息
     * @return
     */
    CertificateType selectByKey(String eId,String id);

    /**
     * 根据企业id和证书id删除证书类型信息
     * @return
     */
    void deleteByKey(String eId,String id);

    /**
     * 统计关联某类型的证书的总数量
     * @return
     */
    int selectCountCertificateType(String eId,String typeId);
}
