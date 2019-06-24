package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetExamUnderListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "考试名称",required = true)
    private String name;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;

    @ApiModelProperty(value = "企业id",required = true)
    private String eId;

}
