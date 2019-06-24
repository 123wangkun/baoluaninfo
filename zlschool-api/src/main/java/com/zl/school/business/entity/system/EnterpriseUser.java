package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_enterprise_user")
public class EnterpriseUser implements Serializable {
	//用户状态--注销
    public static final int STATUS_CANCEL = 0;
    //用户状态--启用
    public static final int STATUS_NORMAL = 1;
    //用户类型--个人用户
    public static final int TYPE_PERSON = 0;
    //用户类型--企业用户
    public static final int TYPE_ENTER = 1;
    //是否注册--未注册
    public static final int STATUS_NotREG = 0;
    //是否注册--已注册
    public static final int STATUS_REG = 1;
	
    //个人企业
    public static final String Default_Personal = "00";
    /**
     * 所属组织
     */
    private String eid;

    /**
     * 所属部门
     */
    private String departId;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 职务
     */
    private String post;

    /**
     * 职称
     */
    private String position;

    /**
     * 允许上传文件大小(默认2048M)
     */
    private Long allowSize;

    /**
     * 已上传文件大小
     */
    private Long makeSize;

    /**
     * 用户总积分
     */
    private Integer integral;

    /**
     * 是否注册（0未注册-导入，1已注册）
     */
    private Integer regStatus;

    /**
     * 用户类型（0个人用户，1企业用户）
     */
    private Integer type;

    /**
     * 用户状态（0表示未启用，1表示启用）
     */
    private Integer status;

    /**
     * 注册时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifydTime;


}
