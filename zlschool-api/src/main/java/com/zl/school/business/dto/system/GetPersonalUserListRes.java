package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetPersonalUserListRes {

    @ApiModelProperty(value = "用户id",required = true)
    private String id;

    @ApiModelProperty(value = "用户真名",required = true)
    private String realName;

    @ApiModelProperty(value = "手机号码",required = true)
    private String telePhone;

    @ApiModelProperty(value = "已上传文件大小",required = true)
    private Long makeSize;

    @ApiModelProperty(value = "用户总积分",required = true)
    private Integer integral;

    @ApiModelProperty(value = "用户状态（0表示未启用，1表示启用）",required = true)
    private Integer status;

    @ApiModelProperty(value = "用户状态（0表示未启用，1表示启用）",required = true)
    private String statusName;

    @ApiModelProperty(value = "注册时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

}
