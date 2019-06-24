package com.zl.school.business.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetOnlineActivityListRes {

    @ApiModelProperty(value = "课程表---线下课程编码",required = true)
    private String id;

    @ApiModelProperty(value = "课程表---线下课程名称",required = true)
    private String courseName;

    @ApiModelProperty(value = "活动表---评论内容",required = true)
    private String comment;

    @ApiModelProperty(value = "活动类型(1浏览;1学习;3点赞;4收藏;5评论)",required = true)
    private Integer type;

    @ApiModelProperty(value = "活动表---学习状态(0未进行;1进行中;3已学完;4未学习)",required = true)
    private Integer state;

    @ApiModelProperty(value = "活动表---创建时间，学习时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "用户表---用户真名",required = true)
    private String realName;

    @ApiModelProperty(value = "企业部门表---部门名称",required = true)
    private String depName;

}
