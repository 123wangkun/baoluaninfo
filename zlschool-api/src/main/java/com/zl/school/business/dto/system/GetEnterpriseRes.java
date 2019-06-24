package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetEnterpriseRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "企业名称",required = true)
    private String name;

    @ApiModelProperty(value = "企业编码",required = true)
    private String code;

    @ApiModelProperty(value = "上级编码",required = true)
    private String parentId;

    @ApiModelProperty(value = "上级名称",required = true)
    private String parentName;

    @ApiModelProperty(value = "允许上传文件大小(默认2048M)",required = true)
    private Long allowSize;

    @ApiModelProperty(value = "已上传文件大小",required = true)
    private Long makeSize;

    @ApiModelProperty(value = "允许使用的用户数(默认10000)",required = true)
    private Integer allowUser;

    @ApiModelProperty(value = "创建人",required = true)
    private Integer makeUser;

    @ApiModelProperty(value = "该企业有效期(默认一年)",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date validate;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:启用 4:禁用",required = true)
    private Integer status;

    @ApiModelProperty(value = "营业执照URL地址",required = true)
    private String license;

    @ApiModelProperty(value = "营业执照有效期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date licenseTerm;

    @ApiModelProperty(value = "创建日期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date created_time;

    @ApiModelProperty(value = "系统名称",required = true)
    private String sysName;

    @ApiModelProperty(value = "系统LOGO",required = true)
    private String sysLogo;

    @ApiModelProperty(value = "邀请链接",required = true)
    private String inviteLink;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:审核通过",required = true)
    private Integer proStatus;

    @ApiModelProperty(value = "状态1:待审核 2:审核不通过 3:审核通过",required = true)
    private String proStatusName;

    @ApiModelProperty(value = "审批内容",required = true)
    private String proContent;

    @ApiModelProperty(value = "审批备注",required = true)
    private String proMemo;

}
