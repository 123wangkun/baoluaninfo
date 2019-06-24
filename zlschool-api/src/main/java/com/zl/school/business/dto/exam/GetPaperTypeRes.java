package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetPaperTypeRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
