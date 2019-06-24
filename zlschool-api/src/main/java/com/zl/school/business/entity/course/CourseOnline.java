package com.zl.school.business.entity.course;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线上课程
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_online")
public class CourseOnline implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 线上课程名称
     */
    private String name;

    /**
     * 课程类型
     */
    private String typeId;

    /**
     * PC图片(线上)
     */
    private String logoSrc;

    /**
     * 手机图片(线上)
     */
    private String mobileSrc;

    /**
     * 文件类型(线上)(1视频,2PDF,3EXCEL,4WORD)
     */
    private Integer fileType;

    /**
     * 文件路径(线上)
     */
    private String fileSrc;

    /**
     * 文件大小(线上)
     */
    private Long fileSize;

    /**
     * 课程简介
     */
    private String descript;

    /**
     * 课程状态(1已发布2未发布;)
     */
    private Integer state;

    /**
     * 显示顺序
     */
    private Integer position;

    /**
     * 课程时长(分钟)
     */
    private Integer totalTime;

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
     * 是否免费（1是2否）
     */
    private Integer isfree;

    /**
     * 是否可用积分兑换（1是2否）
     */
    private Integer isintegral;

    /**
     * 学习所需积分
     */
    private Integer integral;

    /**
     * 是否可用金额兑换（1是2否）
     */
    private Integer ismoney;

    /**
     * 学习所需金额
     */
    private BigDecimal money;

    /**
     * 状态1:待审核 2:审核不通过 3:审核通过
     */
    private Integer proStatus;

}
