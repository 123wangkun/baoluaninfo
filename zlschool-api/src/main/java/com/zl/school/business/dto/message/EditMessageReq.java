package com.zl.school.business.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditMessageReq {

    @ApiModelProperty(value = "消息编码",required = true)
    private String id;

    @ApiModelProperty(value = "标题",required = true)
    private String title;

    @ApiModelProperty(value = "类型名称",required = true)
    private String classname;

    @ApiModelProperty(value = "描述",required = true)
    private String describe;

    @ApiModelProperty(value = "图片(图标)",required = true)
    private String src;

    @ApiModelProperty(value = "内容",required = true)
    private String content;

    @ApiModelProperty(value = "消息类型(1通知;2培训)",required = true)
    private Integer type;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;
}
