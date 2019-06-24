package com.zl.school.business.dto.certificate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCertificateTypeListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;
    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;
}
