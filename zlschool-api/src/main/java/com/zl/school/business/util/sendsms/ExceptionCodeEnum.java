package com.zl.school.business.util.sendsms;

import org.apache.commons.httpclient.HttpStatus;

import java.text.MessageFormat;

/**
 * service错误码
 *
 * @author wangkai
 */
public enum ExceptionCodeEnum {

    /**
     * 系统异常
     */
    SUCCESS(0, "执行成功"),
    SYS_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统异常，请联系管理员"),
    LOGIN_TIME_OUT(HttpStatus.SC_UNAUTHORIZED, "登录超时，请重新登录"),
    PARAM_ERROR(HttpStatus.SC_BAD_REQUEST, "参数异常"),
    NOT_FOND(HttpStatus.SC_NOT_FOUND, "路径不存在，请检查路径是否正确"),
    AUTHORIZATION_ERROR(HttpStatus.SC_FORBIDDEN, "没有权限，请联系管理员授权"),

    /**
     * 无HTTP错误码的全局异常码
     */
    DATA_IS_EXIST(1001, "数据库中已存在该记录"),
    JSON_PARSE_ERROR(1002, "JSON格式错误"),


    /**
     * pc端验证码
     */
    UN_GET_SMS_CODE(1101, "请先获取验证码，再登录"),
    SMS_CODE_ERROR(1102, "验证码输入有误，请重新输入"),
    SMS_CODE_TIMEOUT(1103, "验证码已过期，请重新获取"),

    /**
     * 接口通用
     */
    SYS_CODE_NOT_EXIST_OR_NOT_ENABLE(1201, "业务系统不存在或不可用"),
    IP_NOT_IN_WHITE_LIST(1202, "ip地址不在白名单中，请先配置ip地址"),
    SYS_INFO_IS_NULL_OR_PUSH_URL_IS_NULL(1203, "业务系统不存在或推送地址为空"),

    /**
     * 短链接接口
     */
    LONG_URL_2_SHORT_URL_ERROR(1301, "长链接转换为短链接异常"),
    SHORT_URL_NOT_FOND(1302, "非法的短链接"),
    LONG_URL_IS_NULL(1303, "长链接为空"),
    LONG_URL_IS_EXIST(1304, "长链接已存在"),

    /**
     * 短信接口
     */
    SMS_SIGNATURE_NOT_FOND(1401, "短信签名不存在"),
    SMS_TEMPLATE_NOT_FOND(1402, "短信模板不存在"),
    MSG_IS_NULL(1403, "短信内容不能为空"),
    PARAMS_IS_NULL(1404, "变量参数不能为空"),
    PHONE_IS_NULL(1405, "手机号不能为空"),
    PHONE_LENGTH_NOT_EQ_PARAMS_LENGTH(1406, "手机号码与变量参数个数不一致"),
    VAR_LENGTH_NOT_EQ_PARAMS_LENGTH(1407, "变量参数与占位符个数不一致"),
    VAR_LENGTH_CAN_NOT_IN_CODE_OR_TEXT_TYPE(1408, "验证码类型及文本类型短信不可使用占位符"),
    PUSH_RESPONSE_ERROR(1409, "推送上行短信异常"),
    CODE_TYPE_CLIENT_IP_IS_NULL(1410, "验证码短信客户端ip为空"),


    /**
     * ---------------------------------------------------- 华丽的分割线 ----------------------------------------------------
     */
    ;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 重写构造方法
     *
     * @param code
     * @param message
     */
    ExceptionCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage(Object... args) {
        if (args != null && args.length > 0) {
            return new MessageFormat(message).format(args);
        }
        return message;
    }

    public ExceptionCodeEnum setMessageAndReturn(String message) {
        this.message = message;
        return this;
    }


    @Override
    public String toString() {
        return "ServiceCodeEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
