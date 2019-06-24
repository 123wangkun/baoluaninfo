package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 *
 * @author MrBird
 */
@Data
public class RegisterReq {

    @ApiModelProperty(value = "部门邀请码(个人用户为空)",required = true)
    private String entCode;

    @ApiModelProperty(value = "手机号码(必填)",required = true)
    private String telePhone;

    @ApiModelProperty(value = "用户真名(必填)",required = true)
    private String realName;

    @ApiModelProperty(value = "用户密码(必填)",required = true)
    private String password;

    @ApiModelProperty(value = "出生日期(必填)",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date age;

    @ApiModelProperty(value = "性别(0男1女)(必填)",required = true)
    private int sex;

    @ApiModelProperty(value = "职务(必填)",required = true)
    private String post;

    @ApiModelProperty(value = "邮箱(必填)",required = true)
    private String email;

    @ApiModelProperty(value = "文件列表(可选)",required = true)
    private List<FileDTO> fileList;

    @ApiModelProperty(value = "职称",required = true)
    private String position;

    @Data
    public static class FileDTO{

        @ApiModelProperty(value = "文件所属类型",required = true)
        private String type;

        @ApiModelProperty(value = "文件地址链接",required = true)
        private String url;

        @ApiModelProperty(value = "文件大小",required = true)
        private Long size;

        @ApiModelProperty(value = "文件名称",required = true)
        private String name;

    }
}
