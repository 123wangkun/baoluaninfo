package com.zl.school.business.dto.system;

import lombok.Data;

import java.util.List;
@Data
public class GetMenuTreeRes {
    private String roleId;
    private String roleName;
    private String memo;
    private List<RolePowerMenu> rolePowerTree;
    @Data
    public static class RolePowerMenu{
        private String id;
        private String name;
        private String parentId;
        private List<RolePowerMenu> children;
        private String describe;
        private String note;
        //1表示Menu(菜单)，2表示operator(功能按钮)
        private int type;
        private boolean checked;
    }

}
