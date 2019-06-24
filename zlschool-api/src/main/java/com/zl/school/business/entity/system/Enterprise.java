package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 *
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_enterprise")
public class Enterprise implements Serializable {
    //默认企业允许上传的大小
    public static final Long Default_allowSize = 20480000L;
    //默认企业允许拥有的用户总数
    public static final int Default_allowUser = 1000;
    //企业审核状态  启用/通过
    public static final int Default_Pass = 1;
    //企业审核状态  禁用
    public static final int Default_Disable = 2;
    //企业审核状态  审核通过
    public static final int Pro_Pass = 3;
    //企业审核状态  审核不通过
    public static final int Pro_NotPass = 2;
    //企业审核状态  待审核
    public static final int Pro_Wait = 1;

    /**
     * 编码
     */
    private String id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业编码
     */
    private String code;

    /**
     * 上级编码
     */
    private String parentId;

    /**
     * 允许上传文件大小(默认2048M)
     */
    private Long allowSize;

    /**
     * 已上传文件大小
     */
    private Long makeSize;

    /**
     * 允许使用的用户数(默认10000)
     */
    private Integer allowUser;

    /**
     * 已有用户数
     */
    private Integer makeUser;

    /**
     * 该企业有效期(默认一年)
     */
    private Date validate;

    /**
     * 1:启用 2:禁用
     */
    private Integer status;

    /**
     * 状态1:待审核 2:审核不通过 3:审核通过
     */
    private Integer proStatus;

    /**
     * 营业执照URL地址
     */
    private String license;

    /**
     * 营业执照有效期
     */
    private Date licenseTerm;

    /**
     * 创建人ID
     */
    private String createUserid;

    /**
     * 创建日期
     */
    private Date createdTime;

    /**
     * 更新人ID
     */
    private String updateUserid;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 审批内容
     */
    private String proContent;

    /**
     * 审批时间
     */
    private Date proTime;

    /**
     * 审批人ID
     */
    private String proUserid;

    /**
     * 审批备注
     */
    private String proMemo;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 系统LOGO
     */
    private String sysLogo;

    /**
     * 邀请链接
     */
    private String inviteLink;
}
