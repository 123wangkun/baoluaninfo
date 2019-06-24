package com.zl.school.business.dto.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
public class EditExamUnderReq {

    @ApiModelProperty(value = "编号",required = true)
    private String id;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "线下考试名称",required = true)
    private String name;

    @ApiModelProperty(value = "学习地址(线下)",required = true)
    private String address;

    @ApiModelProperty(value = "开始时间(线下)",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date studyTime;

    @ApiModelProperty(value = "考试时长(分钟)",required = true)
    private Integer totalTime;

    @ApiModelProperty(value = "考试简介",required = true)
    private String descript;

    @ApiModelProperty(value = "考试状态(1发布中;2已结束;3未启用;)",required = true)
    private Integer state;

    @ApiModelProperty(value = "生成二维码路径(线下)",required = true)
    private String qrcodeSrc;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<EditExamUnderLabelList> labelIds;
    @Data
    public static class EditExamUnderLabelList{
        @ApiModelProperty(value = "标签ID",required = true)
        private String labelId;
    }
}
