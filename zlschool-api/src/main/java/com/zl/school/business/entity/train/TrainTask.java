package com.zl.school.business.entity.train;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 培训任务详情
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_train_task")
public class TrainTask implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 关联培训编码
     */
    private String trainId;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 关联课程id或试卷id
     */
    private String relationId;

    /**
     * 任务类型(1线上课程;2线下课程;3线下考试;4线上考试)
     */
    private Integer type;

    /**
     * 任务顺序
     */
    private Integer position;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态(1未开始2进行中3已结束4禁用)
     */
    private String status;

    /**
     * 备注
     */
    private String memo;


}
