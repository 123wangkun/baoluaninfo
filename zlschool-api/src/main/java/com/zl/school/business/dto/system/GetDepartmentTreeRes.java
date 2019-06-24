package com.zl.school.business.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class GetDepartmentTreeRes {

    @ApiModelProperty(value = "目录树",required = true)
    private List<DepartmentTree> depTree;

    @Data
    public static class DepartmentTree{
        @ApiModelProperty(value = "编号",required = true)
        private String id;
        @ApiModelProperty(value = "部门名称",required = true)
        private String name;
        @ApiModelProperty(value = "部门邀请码",required = true)
        private String code;
        @ApiModelProperty(value = "部门邀请链接",required = true)
        private String inviteLink;
        @ApiModelProperty(value = "父级id",required = true)
        private String parentId;
        @ApiModelProperty(value = "children",required = true)
        private List<DepartmentTree> children;
    }

}
