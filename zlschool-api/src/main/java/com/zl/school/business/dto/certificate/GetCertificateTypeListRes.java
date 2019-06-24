package com.zl.school.business.dto.certificate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GetCertificateTypeListRes {

    @ApiModelProperty(value = "证书类型编码",required = true)
    private String id;

    @ApiModelProperty(value = "类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "状态(0禁用;1启用)",required = true)
    private String stateName;

    @ApiModelProperty(value = "获取积分",required = true)
    private Integer integral;

    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

}
