package com.zl.school.business.dto.train;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetTrainFileListRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "资料名称",required = true)
    private String name;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "文件类型(1视频,2PDF,3EXCEL,4WORD)",required = true)
    private Integer fileType;

    @ApiModelProperty(value = "文件路径",required = true)
    private String fileSrc;

    @ApiModelProperty(value = "文件大小",required = true)
    private Long fileSize;

}
