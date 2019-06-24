package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetEnterpriseReq {
    @ApiModelProperty(value = "企业编码")
    private String id;
}
