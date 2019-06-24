package com.zl.school.business.controller.label;

import com.zl.school.business.dto.label.GetCheckLabelTreeRes;
import com.zl.school.business.service.ILabelDetailService;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/label")
@Api(value = "知识管理后台API", description = "标签详情管理", tags = {"LabelDetailController"})
public class LabelDetailController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ILabelDetailService service;


    @ApiOperation(value = "根据id查询标签目录树(1用户;2课程类型;3题库;4试卷;5培训）", notes = "根据课程id标签目录树根据id查询标签目录树(1用户;2课程类型;3题库;4试卷;5培训）")
    @RequestMapping(value = "/getCheckLabelTree/{id}", method = RequestMethod.GET)
    public GetCheckLabelTreeRes getCheckLabelTree(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCheckLabelTree(id,request);
    }
}
