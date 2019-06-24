package com.zl.school.business.entity.course;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线上课程活动
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_online_activity")
public class CourseOnlineActivity implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 线下课程编码
     */
    private String onlineId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动类型(1浏览;1学习;3点赞;4收藏;5评论)
     */
    private Integer type;

    /**
     * 学习状态(0未进行;1进行中;3已学完)
     */
    private Integer state;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 学习时长
     */
    private Integer spendTime;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 备注
     */
    private String memo;


}
