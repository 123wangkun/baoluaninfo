package com.zl.school.business.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class EditCourseOnlineReq {

    @ApiModelProperty(value = "线下课程编号",required = true)
    private String id;

    @ApiModelProperty(value = "线下课程名称",required = true)
    private String name;

    @ApiModelProperty(value = "课程类型",required = true)
    private String typeId;

    @ApiModelProperty(value = "课程简介",required = true)
    private String descript;

    @ApiModelProperty(value = "课程状态(1审核中;2进行中;3已结束;4未启用;)",required = true)
    private Integer state;

    @ApiModelProperty(value = "PC图片(线上)",required = true)
    private String logoSrc;

    @ApiModelProperty(value = "手机图片(线上)",required = true)
    private String mobileSrc;

    @ApiModelProperty(value = "文件类型(线上)(1视频,2PDF,3EXCEL,4WORD))",required = true)
    private  Integer fileType;

    @ApiModelProperty(value = "文件路径(线上)",required = true)
    private String fileSrc;

    @ApiModelProperty(value = "文件大小(线上)",required = true)
    private Long fileSize;

    @ApiModelProperty(value = "课程时长(分钟)",required = true)
    private Integer totalTime;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<OnlineEditLabelList> labelIds;
    @Data
    public static class OnlineEditLabelList{
        @ApiModelProperty(value = "标签ID",required = true)
        private String labelId;
    }


}
