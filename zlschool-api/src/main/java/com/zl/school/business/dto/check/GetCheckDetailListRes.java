package com.zl.school.business.dto.check;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCheckDetailListRes {

    @ApiModelProperty(value = "详情编号",required = true)
    private String id;

    @ApiModelProperty(value = "考核名称",required = true)
    private String checkName;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "考核得分",required = true)
    private Integer checkScore;
}
