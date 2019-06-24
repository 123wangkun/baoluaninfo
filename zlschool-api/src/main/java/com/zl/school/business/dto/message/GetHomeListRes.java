package com.zl.school.business.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetHomeListRes {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "图片地址",required = true)
    private String src;

    @ApiModelProperty(value = "图片状态(1:启用 2:禁用)",required = true)
    private Integer state;

    @ApiModelProperty(value = "创建日期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
