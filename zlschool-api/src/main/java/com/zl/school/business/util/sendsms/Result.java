package com.zl.school.business.util.sendsms;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpStatus;

import java.util.Map;

/**
 * 返回数据
 */
public class Result extends JSONObject {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", ExceptionCodeEnum.SUCCESS.getCode());
        put("msg", ExceptionCodeEnum.SUCCESS.getMessage());
        put("success", true);
        put("data", null);
    }

    public Result setData(Object o) {
        return this.put("data", o);
    }

    public Object getData() {
        return this.get("data");
    }

    public static Result error() {
        return error(ExceptionCodeEnum.SYS_ERROR);
    }

    public static Result error(ExceptionCodeEnum codeEnum, Object... args) {
        return error(codeEnum.getCode(), codeEnum.getMessage(args));
    }

    public static Result error(ExceptionCodeEnum codeEnum) {
        return error(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        r.put("success", false);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
