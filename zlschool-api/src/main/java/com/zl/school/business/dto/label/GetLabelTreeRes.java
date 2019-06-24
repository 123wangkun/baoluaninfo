package com.zl.school.business.dto.label;

import lombok.Data;

import java.util.List;

@Data
public class GetLabelTreeRes {
    private List<LabelTree> labelTrees;
    @Data
    public static class LabelTree {
        private String id;
        private String name;
        private String parentId;
        private List<LabelTree> children;
    }
}
