package com.zl.school.business.dto.check;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCheckDetailListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "考核名称",required = true)
    private String name;

    @ApiModelProperty(value = "考核编号",required = true)
    private String checkId;

}
