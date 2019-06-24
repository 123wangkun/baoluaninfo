package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetPersonalRes {

    @ApiModelProperty(value = "用户id",required = true)
    private String id;

    @ApiModelProperty(value = "用户真名",required = true)
    private String realName;

    @ApiModelProperty(value = "手机号码",required = true)
    private String telePhone;

    @ApiModelProperty(value = "用户状态（0表示未启用，1表示启用）",required = true)
    private int status;

    @ApiModelProperty(value = "允许上传文件总大小",required = true)
    private Long allowSize;
}
