package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EditRoleReq {

    @ApiModelProperty(value = "角色编号",required = true)
    private String id;

    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    @ApiModelProperty(value = "备注",required = true)
    private String memo;

    @ApiModelProperty(value = "菜单列表",required = true)
    private List<MenuList> menuIds;
    @Data
    public static class MenuList{
        @ApiModelProperty(value = "菜单id",required = true)
        private String menuId;

    }
}
