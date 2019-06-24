package com.zl.school.business.entity.course;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 课程类型
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_type")
public class CourseType implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 课程类型名称
     */
    private String name;

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


}
