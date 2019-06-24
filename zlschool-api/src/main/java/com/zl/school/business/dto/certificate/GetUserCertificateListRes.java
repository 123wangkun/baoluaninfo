package com.zl.school.business.dto.certificate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetUserCertificateListRes {

    /**
     * 证书编码
     */
    private String id;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书路径
     */
    private String fileSrc;

    /**
     * 证书类型
     */
    private String typeName;

    /**
     * 证书状态1:待审核 2:审核不通过 3:启用 4:禁用
     */
    private Integer state;

    /**
     * 证书状态名称
     */
    private String stateName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    /**
     * 有效期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date validTime;

}
