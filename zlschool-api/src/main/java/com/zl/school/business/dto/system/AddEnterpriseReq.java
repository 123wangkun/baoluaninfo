package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddEnterpriseReq {

    @ApiModelProperty(value = "企业名称",required = true)
    private String name;

    @ApiModelProperty(value = "企业编码",required = true)
    private String code;

    @ApiModelProperty(value = "上级编码",required = true)
    private String parentId;

    @ApiModelProperty(value = "营业执照URL地址",required = true)
    private String license;

    @ApiModelProperty(value = "营业执照有效期",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date licenseTerm;

    @ApiModelProperty(value = "系统名称",required = true)
    private String sysName;

    @ApiModelProperty(value = "系统LOGO",required = true)
    private String sysLogo;

    @ApiModelProperty(value = "邀请链接",required = true)
    private String inviteLink;
}
