package com.zl.school.business.dto.check;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCheckDetailRes {

    @ApiModelProperty(value = "详情编号",required = true)
    private String id;

    @ApiModelProperty(value = "考核名称",required = true)
    private String name;

    @ApiModelProperty(value = "用户编号",required = true)
    private String userId;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "考核得分",required = true)
    private Integer checkScore;

}
