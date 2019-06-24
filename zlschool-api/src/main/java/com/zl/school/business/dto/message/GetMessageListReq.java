package com.zl.school.business.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetMessageListReq {
    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "标题",required = true)
    private String title;

    @ApiModelProperty(value = "类型名称",required = true)
    private String className;

    @ApiModelProperty(value = "消息类型(1通知;2培训)",required = true)
    private Integer type;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;

    @ApiModelProperty(value = "企业id",required = true)
    private String eId;
}
