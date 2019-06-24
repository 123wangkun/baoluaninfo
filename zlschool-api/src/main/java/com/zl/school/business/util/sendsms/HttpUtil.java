package com.zl.school.business.util.sendsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * http 请求帮助类 todo 也可使用其他http封装工具
 *
 * @author wangKai
 * @version $Id: HttpUtil.java, v 0.1 2017年5月8日 上午10:01:16 wangKai Exp $
 */
@Slf4j
public class HttpUtil {
    public static final String IS_BYTE_DATA = "IS_BYTE_DATA";
    public static final String DEFAULT_CHARSET = "utf-8";


    /**
     * 超时时间
     */
    private static final Integer TIME_OUT = 500000;


    /**
     * post请求
     *
     * @param url     url
     * @param json    json 参数
     * @param pairs   NameValuePair[]
     * @param param   string 参数 ?& 拼接
     * @param charset 字符集
     * @return
     */
    public static Result post(String url, String json, NameValuePair[] pairs, String param, String charset) {

        charset = StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset;
        Date start = new Date();
        // 请求结果
        Result result = Result.ok();
//        LogUtils.debug(log, "调用HttpClinet服务URL, url = {0}", url);

        // 生成 HttpClinet 对象并设置参数
        HttpClient httpClient = new HttpClient();

        // 设置 Http 连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIME_OUT);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIME_OUT);

        // 生成 PostMethod 对象并设置参数
        PostMethod postMethod = new PostMethod(url);

        // 设置 get 请求超时为 5 秒
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);

        // 设置默认请求头
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());


        // 处理请求参数
        try {
            if (pairs != null && pairs.length > 0) {
                postMethod.setQueryString(pairs);
            } else {
                postMethod.setQueryString(param);
            }
            // 非空保护
            if (StringUtils.isNotBlank(json)) {
                RequestEntity requestEntity = new StringRequestEntity(json, "application/json", charset);
                postMethod.setRequestEntity(requestEntity);
            }

//            LogUtils.debug(log, "调用HttpClient参数 json = {0}", json);

            int statusCode = httpClient.executeMethod(postMethod);
            // 判断访问的状态码
            if (statusCode == HttpStatus.SC_OK) {

                result.put("code", statusCode);
                result.put("success", true);

                // 读取 HTTP 响应内容，
                // 读取为字节数组
                InputStream inputStream = postMethod.getResponseBodyAsStream();


                // byte数组时直接返回
                if (StringUtils.equalsIgnoreCase(IS_BYTE_DATA, charset)) {
                    Date end = new Date();

                    byte[] responseBody = IOUtils.toByteArray(inputStream);

//                    LogUtils.debug(log, "调用HttpClinet服务完成,请求结果 result = byte[{0}] 执行时间 {1} ms", responseBody.length, end.getTime() - start.getTime());

                    return result.setData(responseBody);
                }

                String response = IOUtils.toString(inputStream, charset);

//                LogUtils.debug(log, "调用HttpClient返回结果responseBody = {0}", response);

                result.setData(JSON.parse(response));

                Date end = new Date();
//                LogUtils.debug(log, "调用HttpClinet服务完成,请求结果 result = {0} 执行时间 {1} ms", result.toJSONString(), end.getTime() - start.getTime());

            } else {
//                LogUtils.error(log, "请求出错: " + postMethod.getStatusLine(), null);
            }
            return result;

        } catch (UnsupportedEncodingException e) {
//            LogUtils.error(log, "调用远程服务失败", e);
            throw new RuntimeException("调用远程服务失败 - " + e.getMessage());
        } catch (HttpException e) {
//            LogUtils.error(log, "调用HttpClient时发生异常", e);
            throw new RuntimeException("调用HttpClient时发生异常 - " + e.getMessage());
        } catch (IOException e) {
//            LogUtils.error(log, "调用HttpClient时发生异常", e);
            throw new RuntimeException("调用HttpClient时发生异常 - " + e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }
    }



    /**
     * json格式请求
     *
     * @param url  请求地址
     * @param json json数据
     * @return
     */
    public static Result post(String url, JSONObject json) {
        return post(url, json, null);
    }

    /**
     * json方式自定义字符集请求
     *
     * @param url
     * @param json
     * @param charset
     * @return
     */
    public static Result post(String url, JSONObject json, String charset) {
        return post(url, json.toJSONString(), null, null, charset);
    }


}
