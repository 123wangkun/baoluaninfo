package com.zl.school.business.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 线下考试任务表
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_exam_under")
public class ExamUnder implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 线下考试名称
     */
    private String name;

    /**
     * 学习地址(线下)
     */
    private String address;

    /**
     * 开始时间(线下)
     */
    private Date studyTime;

    /**
     * 考试时长(分钟)
     */
    private Integer totalTime;

    /**
     * 考试简介
     */
    private String descript;

    /**
     * 考试状态(1发布中;2已结束;3未启用;)
     */
    private Integer state;

    /**
     * 生成二维码路径(线下)
     */
    private String qrcodeSrc;

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
    private Date createdtTime;

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
