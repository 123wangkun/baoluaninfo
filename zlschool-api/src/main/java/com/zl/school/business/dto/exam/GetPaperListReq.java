package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetPaperListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "试卷类型",required = true)
    private String typeId;

    @ApiModelProperty(value = "发布状态",required = true)
    private Integer releaseStatus;

    @ApiModelProperty(value = "开始状态",required = true)
    private String startStatus;

    @ApiModelProperty(value = "试卷名称",required = true)
    private String name;

    @ApiModelProperty(value = "企业id",required = true)
    private String eId;

}
