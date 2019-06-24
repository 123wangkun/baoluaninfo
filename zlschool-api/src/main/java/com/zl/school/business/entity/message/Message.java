package com.zl.school.business.entity.message;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 消息管理
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_zl_message")
public class Message implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型名称
     */
    private String classname;

    /**
     * 描述
     */
    private String describe;

    /**
     * 图片(图标)
     */
    private String src;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型(1通知;2培训)
     */
    private Integer type;
    /**
     * 状态
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
