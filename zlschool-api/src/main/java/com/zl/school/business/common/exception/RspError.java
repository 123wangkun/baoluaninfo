package com.zl.school.business.common.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: RspError</p>
 * <p>Description: Http错误返回封装</p>
 *
 * @author zhangsl
 */
@Data
public class RspError implements Serializable {
    private int err_code;

    private String err_msg;

    private String request_id;
}
