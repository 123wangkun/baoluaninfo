package com.zl.school.business.common.exception;

import com.google.gson.Gson;
import com.zl.school.business.dao.ExceptionLogMapper;
import com.zl.school.business.entity.ExceptionLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * <p>Title: ControllerExceptionHandler</p>
 * <p>Description: 控制器异常处理</p>
 */
@ControllerAdvice
public abstract class ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    /**
     * 控制器异常处理入口
     *
     * @param e 异常信息
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<RspError> resolveException(Exception e, HttpServletRequest request) {
        LOG.error(e.getMessage(), e);

        RspError rspError = new RspError();
        if (e instanceof HttpRequestMethodNotSupportedException) {
            rspError.setErr_code(ErrorCode.METHOD_NOT_SUPPORT);
            rspError.setErr_msg("运行错误:请求方法不支持");
            return new ResponseEntity<RspError>(rspError, HttpStatus.METHOD_NOT_ALLOWED);
        } else if (e instanceof MissingServletRequestParameterException) {
            rspError.setErr_code(ErrorCode.PARAM_NOT_RIGHT);
            rspError.setErr_msg("运行错误:请求参数不完整");
            return new ResponseEntity<RspError>(rspError, HttpStatus.BAD_REQUEST);
        } else if (e instanceof NullPointerException) {
            String paramJson = new Gson().toJson(request.getParameterMap());

            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();

            ExceptionLog exceptionLog = new ExceptionLog();
            exceptionLog.setActionKey(request.getRequestURI());
            exceptionLog.setExceptionInfo(expMessage);
            exceptionLog.setParam(paramJson);
            exceptionLog.setCreateTime(new Date());
            exceptionLogMapper.insertSelective(exceptionLog);
            rspError.setErr_code(ErrorCode.NULL_POINTER);
            rspError.setErr_msg("运行错误:空值异常");

            return new ResponseEntity<RspError>(rspError, HttpStatus.BAD_REQUEST);
        } else if (e instanceof BusinessException) {
            rspError.setErr_msg(((BusinessException) e).getMsg());
            rspError.setErr_code(((BusinessException) e).getCode());
            if (((BusinessException) e).getCode().equals(ErrorCode.LOGIN_TIME_OUT)) {
                return new ResponseEntity<RspError>(rspError, HttpStatus.UNAUTHORIZED);
            } else if (((BusinessException) e).getCode().equals(ErrorCode.SIGN_ERROR)) {
                return new ResponseEntity<RspError>(rspError, HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<RspError>(rspError, HttpStatus.BAD_REQUEST);
            }
        } else {
            String paramJson = new Gson().toJson(request.getParameterMap());

            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();

            ExceptionLog exceptionLog = new ExceptionLog();
            exceptionLog.setActionKey(request.getPathInfo() + request.getMethod());
            exceptionLog.setExceptionInfo(expMessage);
            exceptionLog.setParam(paramJson);
            exceptionLog.setCreateTime(new Date());
            exceptionLogMapper.insertSelective(exceptionLog);

            rspError.setErr_code(ErrorCode.OTHER_ERROR);
            rspError.setErr_msg("运行错误:其他错误");
            return new ResponseEntity<RspError>(rspError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
