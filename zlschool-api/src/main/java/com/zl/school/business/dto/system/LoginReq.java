package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginReq {

    @ApiModelProperty(value = "手机号码", required = true)
    private String telePhone;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;
}
