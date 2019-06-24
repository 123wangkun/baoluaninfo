package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.system.CheckSmsReq;
import com.zl.school.business.dto.system.SendSmsRes;
import com.zl.school.business.service.ISmsCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
@Validated
@RestController
@RequestMapping("/system")
@Api(value = "sendSmsCode", description = "发送短信", tags = {"SmsCodeController"})
public class SmsCodeController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(SmsCodeController.class);
    @Autowired
    private ISmsCodeService smsCodeService;

    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @RequestMapping(value = "/sendSmsCode/{phone}/{type}", method = RequestMethod.GET)
    public SendSmsRes sendSmsCode(@PathVariable("phone") String phone, @PathVariable("type") int type, HttpServletRequest request) {
        return smsCodeService.sendSmsCode(phone, type);
    }

    @ApiOperation(value = "校验短信验证码", notes = "校验短信验证码")
    @RequestMapping(value = "/checkSmsCode", method = RequestMethod.POST)
    @ApiImplicitParam(name = "checkSmsReq", value = "用户注册", required = true, dataType = "CheckSmsReq")
    public Boolean checkSmsCode(@RequestBody CheckSmsReq checkSmsReq, HttpServletRequest request) {
        return smsCodeService.checkSmsCode(checkSmsReq);
    }
}
