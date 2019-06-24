package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditDepartmentReq {

    @ApiModelProperty(value = "部门编码",required = true)
    private String depId;

    @ApiModelProperty(value = "部门名称",required = true)
    private String name;

    @ApiModelProperty(value = "部门邀请码",required = true)
    private String code;

    @ApiModelProperty(value = "上级编码",required = true)
    private String parentId;

}
