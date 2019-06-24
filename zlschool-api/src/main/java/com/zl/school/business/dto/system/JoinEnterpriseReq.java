package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class JoinEnterpriseReq {

    @ApiModelProperty(value = "部门邀请码(个人用户为空)",required = true)
    private String depCode;

    @ApiModelProperty(value = "手机号码(必填)",required = true)
    private String telePhone;

    @ApiModelProperty(value = "用户真名(必填)",required = true)
    private String realName;

    @ApiModelProperty(value = "职务(必填)",required = true)
    private String post;

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
