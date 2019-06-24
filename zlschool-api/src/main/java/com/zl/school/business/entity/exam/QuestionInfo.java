package com.zl.school.business.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 题目
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question_info")
public class QuestionInfo implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 题目内容
     */
    private String name;

    /**
     * 题型(1单选题2多选题3判断题)
     */
    private Integer type;

    /**
     * 题目图片路径
     */
    private String image;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 正确答案选项(ABCDE YES NO)
     */
    private String answer;

    /**
     * 解析
     */
    private String remake;

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
