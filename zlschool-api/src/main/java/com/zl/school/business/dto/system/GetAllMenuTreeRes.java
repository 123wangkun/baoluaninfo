package com.zl.school.business.dto.system;

import lombok.Data;

import java.util.List;
@Data
public class GetAllMenuTreeRes {
    private List<AllMenuTree> addRoleMenu;
    @Data
    public static class AllMenuTree{
        private String id;
        private String name;
        private String parentId;
        private List<AllMenuTree> children;
        private String describe;
        private String note;
    }
}
