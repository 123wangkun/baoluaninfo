package com.zl.school.business.dto.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetExamUnderUserRes {

    @ApiModelProperty(value = "线下考试名称",required = true)
    private String examUnderName;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "部门名称",required = true)
    private String depName;

    @ApiModelProperty(value = "学习时长",required = true)
    private Integer spendTime;

    @ApiModelProperty(value = "学习状态(0未进行;1进行中;3已学完;4未学习)",required = true)
    private Integer state;

    @ApiModelProperty(value = "状态名称",required = true)
    private String stateName;

    @ApiModelProperty(value = "得分",required = true)
    private Integer totalScore;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;
}
