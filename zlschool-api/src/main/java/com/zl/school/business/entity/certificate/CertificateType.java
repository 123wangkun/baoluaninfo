package com.zl.school.business.entity.certificate;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 证书类型
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_certificate_type")
public class CertificateType implements Serializable {

    /**
     * 类型编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 状态(0禁用;1启用)
     */
    private Integer state;

    /**
     * 获取积分
     */
    private Integer integral;

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
