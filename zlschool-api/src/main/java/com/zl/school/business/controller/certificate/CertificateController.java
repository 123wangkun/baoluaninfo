package com.zl.school.business.controller.certificate;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.service.ICertificateService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/certificate")
@Api(value = "知识管理后台API", description = "证书管理", tags = {"CertificateController"})
public class CertificateController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICertificateService service;

    @ApiOperation(value = "查询证书列表", notes = "查询证书列表")
    @ApiImplicitParam(name = "getCertificateListReq", value = "查询证书列表", required = true, dataType = "GetCertificateListReq")
    @RequestMapping(value = "/getCertificateList", method = RequestMethod.POST)
    public RspPage<GetCertificateListRes> getCertificateList(@RequestBody GetCertificateListReq getCertificateListReq, HttpServletRequest request) {
        return service.getCertificateList(getCertificateListReq,request);
    }


    @ApiOperation(value = "证书审核", notes = "证书审核")
    @ApiImplicitParam(name = "proCertificateReq", value = "证书审核", required = true, dataType = "ProCertificateReq")
    @RequestMapping(value = "/proCertificate", method = RequestMethod.POST)
    public void proCertificate(@RequestBody ProCertificateReq proCertificateReq, HttpServletRequest request) {
        service.proCertificate(proCertificateReq,request);
    }

    @ApiOperation(value = "查询证书详情", notes = "查询证书详情")
    @RequestMapping(value = "/getCertificate/{id}", method = RequestMethod.POST)
    public GetCertificateRes getCertificate(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCertificate(id,request);
    }

    @ApiOperation(value = "根据用户编号查询用户证书列表", notes = "根据用户编号查询用户证书列表")
    @RequestMapping(value = "/getCertificateListByUser/{userId}", method = RequestMethod.POST)
    public List<GetUserCertificateListRes> getCertificateListByUser(@PathVariable("userId") String userId, HttpServletRequest request) {
        return service.getCertificateListByUser(userId,request);
    }
}
