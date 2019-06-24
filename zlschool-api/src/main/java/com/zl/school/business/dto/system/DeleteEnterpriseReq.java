package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteEnterpriseReq {
    @ApiModelProperty(value = "企业编码",required = true)
    private String eId;
}
