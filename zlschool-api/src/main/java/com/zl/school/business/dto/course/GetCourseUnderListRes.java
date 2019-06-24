package com.zl.school.business.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GetCourseUnderListRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "线下课程名称",required = true)
    private String name;

    @ApiModelProperty(value = "课程类型",required = true)
    private String typeName;

    @ApiModelProperty(value = "课程状态名称",required = true)
    private Integer stateName;

    @ApiModelProperty(value = "课程状态(1审核中;2进行中;3已结束;4未启用;)",required = true)
    private Integer state;

    @ApiModelProperty(value = "开始时间(线下)",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date studyTime;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "课程时长(分钟)",required = true)
    private Integer totalTime;

}
