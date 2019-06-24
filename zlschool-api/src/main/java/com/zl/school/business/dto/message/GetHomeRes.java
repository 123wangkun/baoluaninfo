package com.zl.school.business.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetHomeRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "图片地址",required = true)
    private String src;

    @ApiModelProperty(value = "图片状态(1:启用 2:禁用)",required = true)
    private Integer state;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
