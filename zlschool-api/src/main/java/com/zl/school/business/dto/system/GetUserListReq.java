package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author MrBird
 */
@Data
public class GetUserListReq {

    @ApiModelProperty(value = "当前页",required = true)
    int pageNum;

    @ApiModelProperty(value = "每页记录数",required = true)
    int pageSize;

    @ApiModelProperty(value = "用户真名",required = true)
    private String realName;

    @ApiModelProperty(value = "企业id",required = true)
    private String eid;

    @ApiModelProperty(value = "部门id",required = true)
    private String departId;
}
