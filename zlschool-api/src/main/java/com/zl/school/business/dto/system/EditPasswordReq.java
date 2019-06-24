package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EditPasswordReq {

    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;

    @ApiModelProperty(value = "旧密码",required = true)
    private String oldPassword;

    @ApiModelProperty(value = "手机号码",required = true)
    private String telePhone;

}
