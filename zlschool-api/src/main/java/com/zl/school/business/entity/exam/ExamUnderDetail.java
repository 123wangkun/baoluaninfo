package com.zl.school.business.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线下考试详情
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_exam_under_detail")
public class ExamUnderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 线下考试编号
     */
    private String underId;

    /**
     * 用户编号
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
     * 得分
     */
    private Integer totalScore;

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
