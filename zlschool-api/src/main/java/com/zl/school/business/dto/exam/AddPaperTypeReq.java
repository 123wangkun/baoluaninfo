package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddPaperTypeReq {

    @ApiModelProperty(value = "类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
