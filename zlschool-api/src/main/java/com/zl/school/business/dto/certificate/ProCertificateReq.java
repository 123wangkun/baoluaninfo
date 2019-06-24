package com.zl.school.business.dto.certificate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProCertificateReq {

    @ApiModelProperty(value = "证书编码",required = true)
    private String id;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:启用 4:禁用",required = true)
    private Integer proStatus;

    @ApiModelProperty(value = "审批内容",required = true)
    private String proContent;

    @ApiModelProperty(value = "审批备注",required = true)
    private String proMemo;
}
