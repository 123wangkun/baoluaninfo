package com.zl.school.business.common.exception;

/**
 * 业务异常
 * Created by zhangshilin on 2018/1/12.
 */
public class BusinessException extends RuntimeException {
    private String msg;

    private Integer code;

    public BusinessException(Integer errCode, String msg) {
        this.msg = msg;
        this.code = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
