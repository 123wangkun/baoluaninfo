package com.zl.school.business.util.sendsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

public class SendSmsUtil {

    public static Boolean sendSMS(String phone,String msCode,int expiredtime,String clientIp) {
        ReqSmsSend sendEntity = new ReqSmsSend();
        sendEntity.setSysCode("zsxt");
        sendEntity.setSmsType("1");//SMS_CODE("1", "验证码"),
        sendEntity.setPhone(phone);
        sendEntity.setMsg("您的验证码是："+msCode+"("+expiredtime/(60)+"分钟内有效)。非本人操作请忽略。");
        sendEntity.setClientIp(clientIp);
        JSONObject result =HttpUtil.post("http://sms-api.e-jt.cn/api/send", sendEntity.toJson());
        System.out.println("====="+result.getJSONObject("data").get("success"));
        Boolean flag = (Boolean) result.getJSONObject("data").get("success");
        return flag;
    }

}
