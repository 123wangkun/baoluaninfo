package com.zl.school.business.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetHomeListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

}
