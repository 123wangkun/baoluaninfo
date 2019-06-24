package com.zl.school.business.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetLabelListReq {

    @ApiModelProperty(value = "编码",required = true)
    private String parentId;

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

}
