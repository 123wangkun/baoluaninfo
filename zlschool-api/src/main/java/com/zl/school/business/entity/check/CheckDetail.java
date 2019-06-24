package com.zl.school.business.entity.check;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 综合考核详情
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_zl_check_detail")
public class CheckDetail implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 考核编号
     */
    private String checkId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 考核得分
     */
    private Integer checkScore;

    /**
     * 综合得分
     */
    private Integer totalScore;


}
