package com.zl.school.business.controller.exam;

import com.zl.school.business.service.IExamUnderDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/exam")
@Api(value = "知识管理后台API", description = "线下考试详情", tags = {"ExamUnderDetailController"})
public class ExamUnderDetailController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IExamUnderDetailService service;
}
