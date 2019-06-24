package com.zl.school.business.entity.course;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线下课程活动
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_under_activity")
public class CourseUnderActivity implements Serializable {

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
    private String underId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 学习时长
     */
    private Integer spendTime;

    /**
     * 学习状态(0未进行;1进行中;3已学完;4未学习)
     */
    private Integer state;

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
