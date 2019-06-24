package com.zl.school.business.dto.train;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
@Data
public class EditTrainTaskReq {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "任务名称",required = true)
    private String name;

    @ApiModelProperty(value = "关联课程名称或试卷名称",required = true)
    private String relationId;

    @ApiModelProperty(value = "任务类型(1线上课程;2线下课程;3线下考试;4线上考试)",required = true)
    private Integer type;

    @ApiModelProperty(value = "任务顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "结束时间",required = true)
    private Date endTime;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
