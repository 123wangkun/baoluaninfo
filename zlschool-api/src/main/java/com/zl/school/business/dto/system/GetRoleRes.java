package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetRoleRes {

    @ApiModelProperty(value = "所属组织",required = true)
    private String eid;

    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;
}
