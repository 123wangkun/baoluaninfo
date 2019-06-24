package com.zl.school.business.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetQuestionReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "类型(1单选题2多选题3判断题)   必填项",required = true)
    private Integer type;

    @ApiModelProperty(value = "分值",required = true)
    private Integer score;

    @ApiModelProperty(value = "企业编码",required = true)
    private String eId;

    @ApiModelProperty(value = "拼接后的标签id",required = true)
    private String labs;

    @ApiModelProperty(value = "标签id",required = true)
    private List<GetQuestionIds> labIds;
    @Data
    public static class GetQuestionIds {
        private String id;
    }
}
