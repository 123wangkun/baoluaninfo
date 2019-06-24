package com.zl.school.business.dto.label;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class GetQuestionInfoListRes {

    @ApiModelProperty(value = "题目内容",required = true)
    private String name;

    @ApiModelProperty(value = "题型(1单选题2多选题3判断题)",required = true)
    private Integer type;

    @ApiModelProperty(value = "分值",required = true)
    private Integer score;

    @ApiModelProperty(value = "正确答案选项(ABCDE YES NO)",required = true)
    private String answer;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
