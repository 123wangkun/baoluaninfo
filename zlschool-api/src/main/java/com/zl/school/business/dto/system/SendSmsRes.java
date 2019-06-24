package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SendSmsRes {

    @ApiModelProperty(value = "验证码",required = true)
    private String smscode;

    @ApiModelProperty(value = "有效时间(秒)",required = true)
    private int expiredtime;

}
