package com.zl.school.business.dto.certificate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetUserCertificateListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "企业id",required = true)
    private String eId;

    @ApiModelProperty(value = "用户编号",required = true)
    private String userId;

}
