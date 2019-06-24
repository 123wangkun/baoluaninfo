package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_role")
public class Role {
    //个人用户
    public static final String ROLE_PERSON = "000000";
    //超级管理员
    public static final String ROLE_ADMIN = "000001";
    //企业管理员
    public static final String ROLE_ENTER = "000002";
    //企业员工
    public static final String ROLE_STAFF = "000003";

    /**
     * 角色编号
     */
    private String id;

    /**
     * 所属组织
     */
    private String eid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String memo;


}
