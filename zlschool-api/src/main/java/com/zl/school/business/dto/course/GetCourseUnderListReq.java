package com.zl.school.business.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCourseUnderListReq {
    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "课程类型id",required = true)
    private String typeId;

    @ApiModelProperty(value = "课程名称",required = true)
    private String name;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;

    @ApiModelProperty(value = "企业id",required = true)
    private String eid;
}
