package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author MrBird
 */
@Data
public class GetUserReq {

    @ApiModelProperty(value = "用户id",required = true)
    private String id;

}
