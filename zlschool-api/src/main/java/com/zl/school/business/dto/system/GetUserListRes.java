package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class GetUserListRes {
    @ApiModelProperty(value = "用户id",required = true)
    private String id;

    @ApiModelProperty(value = "用户真名",required = true)
    private String name;

    @ApiModelProperty(value = "手机号码",required = true)
    private String telePhone;

    @ApiModelProperty(value = "用户状态（0表示未启用，1表示启用）",required = true)
    private int status;

    @ApiModelProperty(value = "用户状态名称",required = true)
    private String statusName;

    @ApiModelProperty(value = "是否注册（0未注册-导入，1已注册）",required = true)
    private int regStatus;

    @ApiModelProperty(value = "是否注册",required = true)
    private String regStatusName;

    @ApiModelProperty(value = "注册时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

}
