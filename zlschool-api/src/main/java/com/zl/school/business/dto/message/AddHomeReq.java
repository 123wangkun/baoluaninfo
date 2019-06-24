package com.zl.school.business.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AddHomeReq {

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "图片地址",required = true)
    private String src;

    @ApiModelProperty(value = "图片状态(1:启用 2:禁用)",required = true)
    private Integer state;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "类型(1:通知 2:培训)",required = true)
    private Integer type;

}
