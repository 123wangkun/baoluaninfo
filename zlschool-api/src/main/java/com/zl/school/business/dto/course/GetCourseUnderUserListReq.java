package com.zl.school.business.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCourseUnderUserListReq {
    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "课程id",required = true)
    private String id;

    @ApiModelProperty(value = "活动表---学习状态(0未进行;1进行中;3已学完;4未学习)",required = true)
    private Integer state;

    @ApiModelProperty(value = "用户表---用户真名",required = true)
    private String realName;
}
