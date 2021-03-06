package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author MrBird
 */
@Data
public class EditUserReq {
    @ApiModelProperty(value = "企业编号",required = true)
    private String eid;

    @ApiModelProperty(value = "用户编号",required = true)
    private String id;

    @ApiModelProperty(value = "用户名称",required = true)
    private String loginName;

    @ApiModelProperty(value = "用户真名",required = true)
    private String realName;

    @ApiModelProperty(value = "用户密码",required = true)
    private String password;

    @ApiModelProperty(value = "手机号码",required = true)
    private String telePhone;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "出生日期",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date age;

    @ApiModelProperty(value = "职务",required = true)
    private String post;

    @ApiModelProperty(value = "职称",required = true)
    private String position;

    @ApiModelProperty(value = "学历",required = true)
    private String education;

    @ApiModelProperty(value = "学位",required = true)
    private String degree;

    @ApiModelProperty(value = "用户状态（0表示未启用，1表示启用）",required = true)
    private Integer status;

    @ApiModelProperty(value = "备注）",required = true)
    private String memo;

    @ApiModelProperty(value = "允许上传文件大小(默认2048M)",required = true)
    private Long allowSize;

    @ApiModelProperty(value = "用户类型）",required = true)
    private Integer type;

    @ApiModelProperty(value = "性别(0男1女)",required = true)
    private Integer sex;

    @ApiModelProperty(value = "角色列表",required = true)
    private List<RoleList> roleIds;

    public static class RoleList{

        private String roleId;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }
    }

}
