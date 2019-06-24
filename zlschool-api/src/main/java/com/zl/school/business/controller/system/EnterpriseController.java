package com.zl.school.business.controller.system;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.system.*;
import com.zl.school.business.service.IEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MrBird
 */

@Validated
@RestController
@RequestMapping("/system")
@Api(value = "enterprise", description = "企业管理", tags = {"EnterpriseController"})
public class EnterpriseController extends ExceptionController {
    @Autowired
    private IEnterpriseService enterpriseService;

    @ApiOperation(value = "查询企业详情", notes = "查询企业详情")
    @ApiImplicitParam(name = "getEnterpriseReq", value = "组织管理--查询企业详情", required = true, dataType = "GetEnterpriseReq")
    @RequestMapping(value = "/getEnterprise", method = RequestMethod.POST)
    public GetEnterpriseRes getEnterprise(@RequestBody GetEnterpriseReq getEnterpriseReq, HttpServletRequest request) {
        return enterpriseService.getEnterprise(getEnterpriseReq);
    }

    @ApiOperation(value = "我的企业--创建企业", notes = "我的企业--创建企业")
    @ApiImplicitParam(name = "addEnterpriseReq", value = "我的企业--创建企业", required = true, dataType = "AddEnterpriseReq")
    @RequestMapping(value = "/addEnterprise", method = RequestMethod.POST)
    public void addEnterprise(@RequestBody AddEnterpriseReq addEnterpriseReq, HttpServletRequest request) {
         enterpriseService.addEnterprise(addEnterpriseReq,request);
    }

    @ApiOperation(value = "我的企业--删除企业", notes = "我的企业--删除企业")
    @ApiImplicitParam(name = "deleteEnterpriseReq", value = "我的企业--删除部门", required = true, dataType = "DeleteEnterpriseReq")
    @RequestMapping(value = "/deleteEnterprise", method = RequestMethod.POST)
    public void deleteEnterprise(@RequestBody DeleteEnterpriseReq deleteEnterpriseReq,HttpServletRequest request) {
        enterpriseService.deleteEnterprise(deleteEnterpriseReq,request);
    }

    @ApiOperation(value = "组织/企业管理--修改企业", notes = "组织/企业管理--修改企业")
    @ApiImplicitParam(name = "editEnterpriseReq", value = "组织/企业管理--修改企业", required = true, dataType = "EditEnterpriseReq")
    @RequestMapping(value = "/editEnterprise", method = RequestMethod.POST)
    public void editEnterprise(@RequestBody EditEnterpriseReq editEnterpriseReq,HttpServletRequest request) {
         enterpriseService.editEnterprise(editEnterpriseReq,request);
    }

    @ApiOperation(value = "企业管理--审核企业", notes = "组织管理--审核企业")
    @ApiImplicitParam(name = "proEnterpriseReq", value = "组织管理--审核企业", required = true, dataType = "ProEnterpriseReq")
    @RequestMapping(value = "/proEnterprise", method = RequestMethod.POST)
    public void proEnterprise(@RequestBody ProEnterpriseReq proEnterpriseReq, HttpServletRequest request) {
        enterpriseService.proEnterprise(proEnterpriseReq,request);
    }

    @ApiOperation(value = "企业管理--查询企业列表", notes = "企业管理--查询企业列表")
    @ApiImplicitParam(name = "getEnterpriseListReq", value = "组织管理--查询企业详情", required = true, dataType = "GetEnterpriseListReq")
    @RequestMapping(value = "/getEnterpriseList", method = RequestMethod.POST)
    public RspPage<GetEnterpriseListRes> getEnterpriseList(@RequestBody GetEnterpriseListReq getEnterpriseListReq, HttpServletRequest request) {
        return enterpriseService.getEnterpriseList(getEnterpriseListReq);
    }

    @ApiOperation(value = "创建企业--查询我的企业列表", notes = "创建企业--查询我的企业列表")
    @RequestMapping(value = "/getMyEnterpriseList", method = RequestMethod.POST)
    public List<GetEnterpriseListRes> getMyEnterpriseList(HttpServletRequest request) {
        return enterpriseService.getMyEnterpriseList(request);
    }
}
