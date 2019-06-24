package com.zl.school.business.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetCourseOnlineListRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "线下课程名称",required = true)
    private String name;

    @ApiModelProperty(value = "课程类型",required = true)
    private String typeName;

    @ApiModelProperty(value = "课程状态(1审核中;2进行中;3已结束;4未启用;)",required = true)
    private Integer state;

    @ApiModelProperty(value = "收藏总数量",required = true)
    private Integer collectionNumber;

    @ApiModelProperty(value = "点赞总数量",required = true)
    private Integer likeNumber;

    @ApiModelProperty(value = "学习总人数",required = true)
    private Integer studyNumber;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:审核已通过",required = true)
    private Integer proStatus;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:启用 4:禁用",required = true)
    private String statusName;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

}
