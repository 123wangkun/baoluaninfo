package com.zl.school.business.dto.system;

import lombok.Data;
import java.util.List;
@Data
public class GetUserPowerRes {

    private List<UserPowerTree> userPowerTree;
    @Data
    public static class UserPowerTree{
        private String id;
        private String name;
        private String parentId;
        private List<UserPowerTree> children;
        private String describe;
        private String note;
    }

}
