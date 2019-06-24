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
@TableName("t_sys_department")
public class Department implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 所属组织
     */
    private String eid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门邀请码
     */
    private String code;

    /**
     * 上级编码
     */
    private String parentId;

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
     * 邀请链接
     */
    private String inviteLink;
}
