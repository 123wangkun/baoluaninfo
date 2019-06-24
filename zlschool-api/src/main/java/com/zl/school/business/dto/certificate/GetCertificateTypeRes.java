package com.zl.school.business.dto.certificate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCertificateTypeRes {
    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "证书类型编码",required = true)
    private String id;

    @ApiModelProperty(value = "类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "状态(0禁用;1启用)",required = true)
    private Integer state;

    @ApiModelProperty(value = "获取积分",required = true)
    private Integer integral;

}
