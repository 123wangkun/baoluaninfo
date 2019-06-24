package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author MrBird
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_sms_code")
public class SmsCode implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码类型(1->注册, 2->修改密码)
     */
    private Integer type;

    /**
     * 验证码
     */
    private String smscode;

    /**
     * 请求时间
     */
    private Date requestdt;

    /**
     * 有效时间(秒)
     */
    private Integer expiredtime;


}
