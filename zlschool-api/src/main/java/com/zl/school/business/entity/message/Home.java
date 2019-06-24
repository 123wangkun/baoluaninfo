package com.zl.school.business.entity.message;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * APP首页轮播图片管理
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_zl_home")
public class Home implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String src;

    /**
     * 图片状态(1:启用 2:禁用)
     */
    private Integer state;

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
