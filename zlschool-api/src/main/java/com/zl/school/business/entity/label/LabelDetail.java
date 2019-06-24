package com.zl.school.business.entity.label;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 拥有标签详情
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_zl_label_detail")
public class LabelDetail implements Serializable {
    //用户
    public static final int user_type = 1;
    //课程
    public static final int course_type = 2;
    //题目
    public static final int question_type = 3;
    //试卷
    public static final int paper_type = 4;
    //培训
    public static final int train_type = 5;
    //线下考试
    public static final int exam_type = 6;
    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 标签编码
     */
    private String lableId;

    /**
     * 关联编码
     */
    private String relationId;

    /**
     * 消息类型(1用户;2课程类型;3题库;4试卷;5培训)
     */
    private Integer type;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 创建日期
     */
    private Date createdTime;


}
