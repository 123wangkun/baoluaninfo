package com.zl.school.business.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddCourseTypeReq {

    @ApiModelProperty(value = "课程类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;
}
