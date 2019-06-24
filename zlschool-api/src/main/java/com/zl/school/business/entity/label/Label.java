package com.zl.school.business.entity.label;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 标签管理
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_zl_label")
public class Label implements Serializable {

    /**
     * 标签编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 上级编码
     */
    private String parentId;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 创建日期
     */
    private Date createdTime;

    /**
     * 更新人ID
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 备注
     */
    private String memo;


}
