package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetEnterpriseListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "企业名称", required = true)
    private String name;

    @ApiModelProperty(value = "审核状态", required = true)
    private int proStatus;

    @ApiModelProperty(value = "状态", required = true)
    private int status;
}
