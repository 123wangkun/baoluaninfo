package com.zl.school.business.dto.label;

import lombok.Data;
import java.util.List;

@Data
public class GetCheckLabelTreeRes {
    private List<CheckLabelTree> labelTree;
    @Data
    public static class CheckLabelTree{
        private String id;
        private String name;
        private String parentId;
        private List<CheckLabelTree> children;
        private boolean checked;
    }
}
