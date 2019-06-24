package com.zl.school.business.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetQuestionInfoListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "类型",required = true)
    private Integer type;

    @ApiModelProperty(value = "企业id",required = true)
    private String eId;
}
