package com.zl.school.business.controller.certificate;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.certificate.*;
import com.zl.school.business.service.ICertificateTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/certificate")
@Api(value = "知识管理后台API", description = "证书类型管理", tags = {"CertificateTypeController"})
public class CertificateTypeController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICertificateTypeService service;

    @ApiOperation(value = "查询证书类型列表", notes = "查询证书类型列表")
    @ApiImplicitParam(name = "getCerTypeListReq", value = "查询证书类型列表", required = true, dataType = "GetCertificateTypeListReq")
    @RequestMapping(value = "/getCertificateTypeList", method = RequestMethod.POST)
    public RspPage<GetCertificateTypeListRes> getCertificateTypeList(@RequestBody GetCertificateTypeListReq getCerTypeListReq, HttpServletRequest request) {
        return service.getCertificateTypeList(getCerTypeListReq,request);
    }

    @ApiOperation(value = "新增证书类型", notes = "新增证书类型")
    @ApiImplicitParam(name = "addCertificateTypeReq", value = "新增证书类型", required = true, dataType = "AddCertificateTypeReq")
    @RequestMapping(value = "/addCertificateType", method = RequestMethod.POST)
    public void addCertificateType(@RequestBody AddCertificateTypeReq addCertificateTypeReq, HttpServletRequest request) {
        service.addCertificateType(addCertificateTypeReq,request);
    }

    @ApiOperation(value = "修改证书类型", notes = "修改证书类型")
    @ApiImplicitParam(name = "editCertificateTypeReq", value = "修改证书类型", required = true, dataType = "EditCertificateTypeReq")
    @RequestMapping(value = "/editCertificateType", method = RequestMethod.POST)
    public void editCertificateType(@RequestBody EditCertificateTypeReq editCertificateTypeReq, HttpServletRequest request) {
        service.editCertificateType(editCertificateTypeReq,request);
    }

    @ApiOperation(value = "查询证书类型详情", notes = "查询证书类型详情")
    @RequestMapping(value = "/getCertificateType/{id}", method = RequestMethod.POST)
    public GetCertificateTypeRes getCertificateType(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCertificateType(id,request);
    }

    @ApiOperation(value = "删除证书类型", notes = "删除证书类型")
    @RequestMapping(value = "/deleteCertificateType/{id}", method = RequestMethod.POST)
    public void deleteCertificateType(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCertificateType(id,request);
    }
}
