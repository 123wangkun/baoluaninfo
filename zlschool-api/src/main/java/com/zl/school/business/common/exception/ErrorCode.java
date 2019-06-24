package com.zl.school.business.common.exception;

/**
 * 错误码说明
 * Created by zhangshilin on 2017/12/12.
 */
public interface ErrorCode {
    //***********运行错误码**************//
    Integer METHOD_NOT_SUPPORT = 20001; //运行错误:请求方法不支持

    Integer PARAM_NOT_RIGHT = 20002;  //运行错误:请求参数不完整

    Integer NULL_POINTER = 20003; //运行错误:空值异常

    Integer OTHER_ERROR = 20004; //运行错误:其他错误

    //***********业务错误码**************//
    Integer ADD_DATA_FAIL = 10001; //新增数据失败

    Integer UPDATE_DATA_FAIL = 10002; //更新数据失败

    Integer DELETE_DATA_FAIL = 10003; //删除数据失败

    Integer DATA_NOT_FOUND = 10004; //数据不存在

    Integer NO_PHONE_EXIST = 30001; //账号不存在

    Integer ERROR_PASSWORD = 30002; //密码错误

    Integer ERROR_STATUS = 30003; //账号已禁用

    Integer ERROR_PHONE = 30004; //错误手机号码

    Integer ERROR_FILE = 30005; //上传文件相关错误

    Integer LOGIN_TIME_OUT = 30006; //登陆超时

    Integer SIGN_ERROR = 30007; //信号错误
}
