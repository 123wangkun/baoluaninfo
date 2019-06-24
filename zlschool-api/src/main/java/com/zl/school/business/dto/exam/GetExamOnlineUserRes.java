package com.zl.school.business.dto.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetExamOnlineUserRes {

    @ApiModelProperty(value = "线上考试编号",required = true)
    private String examOnlineId;

    @ApiModelProperty(value = "线上考试名称",required = true)
    private String examOnlineName;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userId;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "部门名称",required = true)
    private String depName;

    @ApiModelProperty(value = "得分",required = true)
    private Integer totalScore;

    @ApiModelProperty(value = "开始时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

}
