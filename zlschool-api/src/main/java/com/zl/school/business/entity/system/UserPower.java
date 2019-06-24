package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 *
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user_power")
public class UserPower implements Serializable {

    //个人用户
    public static final String Personal = "000000";
    //超级管理员
    public static final String Super_Admin = "000001";
    //企业管理员
    public static final String Ent_Admin = "000002";
    //员工
    public static final String Ent_Staff = "000003";

    /**
     * 所属组织
     */
    private String eid;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 角色编号
     */
    private String roleId;

}
