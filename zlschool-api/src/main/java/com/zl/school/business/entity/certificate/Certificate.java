package com.zl.school.business.entity.certificate;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 证书中心
 *
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_certificate")
public class Certificate implements Serializable {
    //待审核
    public static final int status_wait = 1;
    //审核不通过
    public static final int status_not = 2;
    //审核已通过/启用
    public static final int status_pass = 3;
    //禁用
    public static final int status_disable = 4;

    /**
     * 证书编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书路径
     */
    private String fileSrc;

    /**
     * 文件大小(线上)
     */
    private Long fileSize;

    /**
     * 证书类型
     */
    private String typeId;

    /**
     * 证书状态1:启用 2:禁用
     */
    private Integer state;

    /**
     * 有效期
     */
    private Date validTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 更新人ID
     */
    private String updateUser;

    /**
     * 审批内容
     */
    private String proContent;

    /**
     * 审批时间
     */
    private Date prodTime;

    /**
     * 审批人ID
     */
    private String proUser;

    /**
     * 审批备注
     */
    private String proMemo;

    /**
     * 证书状态1:待审核 2:审核不通过 3:审核通过
     */
    private Integer proStatus;
}
