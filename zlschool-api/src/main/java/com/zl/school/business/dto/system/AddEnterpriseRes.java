package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddEnterpriseRes {
    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "企业名称",required = true)
    private String name;

    @ApiModelProperty(value = "上级编码",required = true)
    private String parentId;
}
