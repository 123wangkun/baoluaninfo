package com.zl.school.business.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetProCourseOnlineRes {
    @ApiModelProperty(value = "课程编号",required = true)
    private String id;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:审核通过",required = true)
    private Integer proStatus;

    @ApiModelProperty(value = "审批内容",required = true)
    private String proContent;

    @ApiModelProperty(value = "审批备注",required = true)
    private String proMemo;

}
