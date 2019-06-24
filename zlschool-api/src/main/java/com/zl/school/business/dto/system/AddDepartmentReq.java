package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDepartmentReq {

    @ApiModelProperty(value = "部门名称",required = true)
    private String name;

    @ApiModelProperty(value = "部门邀请码",required = true)
    private String code;

    @ApiModelProperty(value = "上级编码",required = true)
    private String parentId;
}
