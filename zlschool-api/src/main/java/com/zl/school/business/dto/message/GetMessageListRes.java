package com.zl.school.business.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetMessageListRes {
    @ApiModelProperty(value = "消息编码",required = true)
    private String id;

    @ApiModelProperty(value = "标题",required = true)
    private String title;

    @ApiModelProperty(value = "类型名称",required = true)
    private String classname;

    @ApiModelProperty(value = "状态",required = true)
    private Integer state;

    @ApiModelProperty(value = "创建日期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;
}
