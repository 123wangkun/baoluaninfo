package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南京深卡网络技术有限公司
 */
@Validated
@RestController
@RequestMapping("/sys")
@Api(value = "知识管理后台API", description = "用户角色--相关API", tags = {"UserPowerController"})
public class UserPowerController extends ExceptionController {

}
