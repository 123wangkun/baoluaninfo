package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_param")
public class Param implements Serializable {
    /**
     * 编码
     */
    private String id;

    /**
     * 所属组织
     */
    private String eid;

    /**
     * 所属用户(0表示系统全局参数)
     */
    private String userId;

    /**
     * 参数名
     */
    private String pname;

    /**
     * 参数值
     */
    private String pvalue;

    /**
     * 备注说明
     */
    private String memo;


}
