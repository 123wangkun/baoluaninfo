package com.zl.school.business.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 试卷
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_paper")
public class Paper implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 试卷类型
     */
    private String typeId;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 及格线
     */
    private Integer passLine;

    /**
     * 考试总时长
     */
    private Integer spendTime;

    /**
     * 开始状态(0未开始1已开始2已结束)
     */
    private Integer startStatus;

    /**
     * 发布状态(0未发布1已发布)
     */
    private Integer releaseStatus;

    /**
     * 通过后可以获取的积分数量
     */
    private Integer integral;

    /**
     * 单选题数
     */
    private Integer single;

    /**
     * 多选题数
     */
    private Integer multiple;

    /**
     * 判断题数
     */
    private Integer judge;

    /**
     * 显示顺序
     */
    private Integer position;

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
     * 备注
     */
    private String memo;


}
