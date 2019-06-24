package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteResourcesReq {
    @ApiModelProperty(value = "图片路径",required = true)
    String fileUrl;
}
