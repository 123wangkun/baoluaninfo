package com.zl.school.business.entity.course;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线下课程
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_under")
public class CourseUnder implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 线下课程名称
     */
    private String name;

    /**
     * 课程类型
     */
    private String typeId;

    /**
     * 课程简介
     */
    private String descript;

    /**
     * 课程状态(1审核中;2进行中;3已结束;4未启用;)
     */
    private Integer state;

    /**
     * 生成二维码路径(线下)
     */
    private String qrcodeSrc;

    /**
     * 学习地址(线下)
     */
    private String address;

    /**
     * 开始时间(线下)
     */
    private Date studyTime;

    /**
     * 课程时长(分钟)
     */
    private Integer totalTime;

    /**
     * 显示顺序
     */
    private Integer position;

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
     * 状态1:待审核 2:审核不通过 3:审核通过
     */
    private Integer proStatus;

}
