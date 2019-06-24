package com.zl.school.business.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EditQuestionInfoReq {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "题目内容",required = true)
    private String name;

    @ApiModelProperty(value = "题型(1单选题2多选题3判断题)",required = true)
    private Integer type;

    @ApiModelProperty(value = "题目图片路径",required = true)
    private String image;

    @ApiModelProperty(value = "分值",required = true)
    private Integer score;

    @ApiModelProperty(value = "正确答案选项(ABCDE YES NO)",required = true)
    private String answer;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "选项列表",required = true)
    private List<EditOptionsList> optionsList;
    @Data
    public static class EditOptionsList {
        @ApiModelProperty(value = "选项编号",required = true)
        private String id;
        @ApiModelProperty(value = "选项名称",required = true)
        private String optionsId;
        @ApiModelProperty(value = "选项内容",required = true)
        private String content;
    }

    @ApiModelProperty(value = "标签列表",required = true)
    private List<EditQuestionLabelList> labelIds;
    @Data
    public static class EditQuestionLabelList{
        @ApiModelProperty(value = "标签ID",required = true)
        private String labelId;
    }

}
