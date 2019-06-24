package com.zl.school.business.dto.label;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetLabelListRes {

    @ApiModelProperty(value = "标签编码",required = true)
    private String id;

    @ApiModelProperty(value = "标签名称",required = true)
    private String name;

    @ApiModelProperty(value = "上级标签名称",required = true)
    private String parentName;

    @ApiModelProperty(value = "创建日期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

}
