package com.zl.school.business.util.sendsms;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author wagk
 * @Title: ChuangLanSmsSendEntity
 * @ProjectName renren-fast
 * @Description: 创蓝短信发送参数
 * @date 2018-10-18 18:44
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqSmsSend {


    /**
     * 所属系统
     */
    @NotEmpty(message = "所属系统不能为空")
    private String sysCode;

    /**
     * 短信类型
     */
    private String smsType;

    /**
     * 客户端ip，验证码类型短信必填
     */
    private String clientIp;


    /**
     * 签名 未传递签名将使用默认签名
     */
    private String signature;


    /**
     * 模板id
     */
    private String templateId;


    /**
     * 该条短信在您业务系统内的 ID， 如订单号或者短信发送记录流水号， 选填
     */
    private String uid;


    /**
     * 定时发送短信时间。 格式为 yyyyMMddHHmm， 值小于或等于当前时间则立即发送， 默认立即发送， 选填
     */
    @JsonFormat(pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @JSONField(format = "yyyyMMddHHmmss")
    private Date sendtime;


    /**
     * 短信内容。 长度不能超过 536 个字符
     */
    @Length(max = 536, message = "短信内容不能超过536个字符")
    private String msg;

    /**
     * 手机号码。 多个手机号码使用英文逗号分隔， 必填
     */
    private String phone;

    /**
     * 变量参数
     */
    private String params;


    public JSONObject toJson() {
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }

}
