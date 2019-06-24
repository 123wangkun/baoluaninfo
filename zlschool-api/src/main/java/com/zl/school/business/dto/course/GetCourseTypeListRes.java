package com.zl.school.business.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetCourseTypeListRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "课程类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;
}
