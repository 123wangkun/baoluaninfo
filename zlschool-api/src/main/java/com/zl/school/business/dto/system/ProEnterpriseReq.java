package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProEnterpriseReq {

    @ApiModelProperty(value = "企业编码",required = true)
    private String entId;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3审核通过",required = true)
    private Integer proStatus;

    @ApiModelProperty(value = "审批内容",required = true)
    private String proContent;

    @ApiModelProperty(value = "审批备注",required = true)
    private String proMemo;

}
