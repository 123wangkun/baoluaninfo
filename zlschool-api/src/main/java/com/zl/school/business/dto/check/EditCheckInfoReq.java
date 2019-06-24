package com.zl.school.business.dto.check;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class EditCheckInfoReq {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "考核名称",required = true)
    private String name;

    @ApiModelProperty(value = "考核内容",required = true)
    private String content;

    @ApiModelProperty(value = "考核时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
