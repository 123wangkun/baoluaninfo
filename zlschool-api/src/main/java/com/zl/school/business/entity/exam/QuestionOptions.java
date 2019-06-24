package com.zl.school.business.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 题目答案选项
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_question_options")
public class QuestionOptions implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 关联问题id
     */
    private String questionId;

    /**
     * 选项号ABCDE
     */
    private String optionsId;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 显示顺序
     */
    private Integer position;


}
