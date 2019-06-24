package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CheckSmsReq {

    @ApiModelProperty(value = "验证码",required = true)
    private String smsCode;

    @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    @ApiModelProperty(value = "类型",required = true)
    private int type;
}
