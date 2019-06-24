package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetExamUnderUserReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "用户名称",required = true)
    private String name;

    @ApiModelProperty(value = "线下考试编号",required = true)
    private String id;


}
