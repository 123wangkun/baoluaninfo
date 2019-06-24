package com.zl.school.business.controller.check;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.service.ICheckDetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zl.school.business.common.exception.ExceptionController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */

@Validated
@RestController
@RequestMapping("/check")
@Api(value = "知识管理后台API", description = "考核详情管理", tags = {"CheckDetailController"})
public class CheckDetailController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ICheckDetailService service;

    @ApiOperation(value = "根据考核id查询考核详情列表", notes = "根据培训id查询考核详情列表")
    @ApiImplicitParam(name = "getCheckDetailListReq", value = "修改考核详情", required = true, dataType = "GetCheckDetailListReq")
    @RequestMapping(value = "/getCheckDetailList", method = RequestMethod.GET)
    public RspPage<GetCheckDetailListRes> getCheckDetailList(@RequestBody GetCheckDetailListReq getCheckDetailListReq, HttpServletRequest request) {
        return service.getCheckDetailList(getCheckDetailListReq,request);
    }

    @ApiOperation(value = "新增考核详情", notes = "新增考核详情")
    @ApiImplicitParam(name = "addCheckDetailReq", value = "新增考核详情", required = true, dataType = "AddCheckDetailReq")
    @RequestMapping(value = "/addCheckDetail", method = RequestMethod.POST)
    public void addCheckDetail(@RequestBody AddCheckDetailReq addCheckDetailReq, HttpServletRequest request) {
        service.addCheckDetail(addCheckDetailReq,request);
    }

    @ApiOperation(value = "修改考核详情", notes = "修改考核详情")
    @ApiImplicitParam(name = "editCheckDetailReq", value = "修改考核详情", required = true, dataType = "EditCheckDetailReq")
    @RequestMapping(value = "/editCheckDetail", method = RequestMethod.POST)
    public void editCheckDetail(@RequestBody EditCheckDetailReq editCheckDetailReq, HttpServletRequest request) {
        service.editCheckDetail(editCheckDetailReq,request);
    }

    @ApiOperation(value = "查询考核详情详情", notes = "查询考核详情详情")
    @RequestMapping(value = "/getCheckDetail/{id}", method = RequestMethod.GET)
    public GetCheckDetailRes getCheckDetail(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getCheckDetail(id,request);
    }

    @ApiOperation(value = "删除考核详情", notes = "删除考核详情")
    @RequestMapping(value = "/deleteCheckDetail/{id}", method = RequestMethod.GET)
    public void deleteCheckDetail(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteCheckDetail(id,request);
    }

}
