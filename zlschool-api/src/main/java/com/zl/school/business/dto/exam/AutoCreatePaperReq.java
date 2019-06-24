package com.zl.school.business.dto.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AutoCreatePaperReq {

    @ApiModelProperty(value = "试卷类型",required = true)
    private String typeId;

    @ApiModelProperty(value = "试卷名称",required = true)
    private String name;

    @ApiModelProperty(value = "试卷总分",required = true)
    private Integer totalScore;

    @ApiModelProperty(value = "及格线",required = true)
    private Integer passLine;

    @ApiModelProperty(value = "考试总时长",required = true)
    private Integer spendTime;

    @ApiModelProperty(value = "开始状态(0未开始1已开始2已结束)",required = true)
    private Integer startStatus;

    @ApiModelProperty(value = "发布状态(0未发布1已发布)",required = true)
    private Integer releaseStatus;

    @ApiModelProperty(value = "通过后可以获取的积分数量",required = true)
    private Integer integral;

    @ApiModelProperty(value = "单选题数",required = true)
    private Integer single;

    @ApiModelProperty(value = "多选题数",required = true)
    private Integer multiple;

    @ApiModelProperty(value = "判断题数",required = true)
    private Integer judge;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "标签id",required = true)
    private List<AutoLabIds> labIds;
    @Data
    public static class AutoLabIds {
        private String id;
    }
}
