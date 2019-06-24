package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetSessionRes {
    @ApiModelProperty(value = "登陆用户",required = true)
    private UserDto user;

    @Data
    public static class UserDto {

        @ApiModelProperty(value = "企业编码",required = true)
        private String eid;

        @ApiModelProperty(value = "用户id",required = true)
        private String id;

        @ApiModelProperty(value = "用户真名",required = true)
        private String realName;

        @ApiModelProperty(value = "手机号码",required = true)
        private String telePhone;

        @ApiModelProperty(value = "邮箱",required = true)
        private String email;

        @ApiModelProperty(value = "出生日期",required = true)
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        private Date age;
    }
}
