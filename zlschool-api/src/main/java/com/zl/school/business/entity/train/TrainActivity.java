package com.zl.school.business.entity.train;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 岗位培训活动
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_train_activity")
public class TrainActivity implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 关联用户id
     */
    private String userId;

    /**
     * 培训id
     */
    private String trainId;

    /**
     * 培训任务id
     */
    private String taskId;

    /**
     * 1未开始2进行中3已学完4已结束5未学习
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


}
