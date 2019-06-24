package com.zl.school.business.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditLabelReq {

    @ApiModelProperty(value = "标签编码",required = true)
    private String id;

    @ApiModelProperty(value = "标签名称",required = true)
    private String name;

    @ApiModelProperty(value = "上级标签编码",required = true)
    private String parentId;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;
}
