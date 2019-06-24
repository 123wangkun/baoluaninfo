package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class User implements Serializable {

    /**
     * 用户编号
     */
    private String id;

    /**
     * 手机号码
     */
    private String telePhone;

    /**
     * 用户真名
     */
    private String realName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private Date age;

    /**
     * 学历
     */
    private String education;

    /**
     * 性别
     */
    private int sex;

    /**
     * 学位
     */
    private String degree;

    /**
     * 注册时间
     */
    private Date createdTime;

    /**
     * 最后修改时间
     */
    private Date modifydTime;

    /**
     * 最后登陆时间
     */
    private Date lastLogin;

    /**
     * 最后一次登陆组织
     */
    private String lastEid;

    /**
     * 备注
     */
    private String memo;

    /**
     * 映射微信OpenID
     */
    private String wxOpenId;

    /**
     * 映射支付宝UserID
     */
    private String aliUserId;

    /**
     * 用户头像
     */
    private String headIcon;
}
