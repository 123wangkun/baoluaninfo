package com.zl.school.business.dto.train;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetTrainTaskReq {

    @ApiModelProperty(value = "任务id",required = true)
    private String relationId;

    @ApiModelProperty(value = "类型",required = true)
    private Integer type;

}
