package com.zl.school.business.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class EditCourseUnderReq {

    @ApiModelProperty(value = "线下课程编码",required = true)
    private String id;

    @ApiModelProperty(value = "线下课程名称",required = true)
    private String name;

    @ApiModelProperty(value = "课程类型",required = true)
    private String typeId;

    @ApiModelProperty(value = "课程简介",required = true)
    private String descript;

    @ApiModelProperty(value = "课程状态(1审核中;2进行中;3已结束;4未启用;)",required = true)
    private Integer state;

    @ApiModelProperty(value = "学习地址(线下)",required = true)
    private String address;

    @ApiModelProperty(value = "开始时间(线下)",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date studyTime;

    @ApiModelProperty(value = "课程时长(分钟)",required = true)
    private Integer totalTime;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<EditLabelList> editLabelLists;
    @Data
    public static class EditLabelList{
        @ApiModelProperty(value = "标签ID",required = true)
        private String labelId;
    }
}
