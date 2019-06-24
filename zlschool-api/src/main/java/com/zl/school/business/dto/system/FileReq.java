package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FileReq {

    @ApiModelProperty(value = "文件所属类型",required = true)
    private String type;

    @ApiModelProperty(value = "文件地址链接",required = true)
    private String url;

    @ApiModelProperty(value = "文件大小",required = true)
    private Long size;
}
