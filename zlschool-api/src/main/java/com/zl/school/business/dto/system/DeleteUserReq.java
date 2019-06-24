package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author MrBird
 */
@Data
public class DeleteUserReq {

    @ApiModelProperty(value = "用户编号",required = true)
    private String userId;


}
