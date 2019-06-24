package com.zl.school.business.controller.message;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.service.IHomeService;
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
@RequestMapping("/message")
@Api(value = "知识管理后台API", description = "APP首页轮播图管理", tags = {"HomeController"})
public class HomeController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IHomeService service;

    @ApiOperation(value = "查询轮播图片列表", notes = "查询轮播图片列表")
    @ApiImplicitParam(name = "getHomeListReq", value = "查询轮播图片列表", required = true, dataType = "GetHomeListReq")
    @RequestMapping(value = "/getHomeList", method = RequestMethod.POST)
    public RspPage<GetHomeListRes> getHomeList(@RequestBody GetHomeListReq getHomeListReq, HttpServletRequest request) {
        return service.getHomeList(getHomeListReq,request);
    }

    @ApiOperation(value = "新增轮播图片", notes = "新增轮播图片")
    @ApiImplicitParam(name = "addHomeReq", value = "新增轮播图片", required = true, dataType = "AddHomeReq")
    @RequestMapping(value = "/addHome", method = RequestMethod.POST)
    public void addHome(@RequestBody AddHomeReq addHomeReq, HttpServletRequest request) {
        service.addHome(addHomeReq,request);
    }

    @ApiOperation(value = "修改轮播图片", notes = "修改轮播图片")
    @ApiImplicitParam(name = "editHomeReq", value = "修改轮播图片", required = true, dataType = "EditHomeReq")
    @RequestMapping(value = "/editHome", method = RequestMethod.POST)
    public void editHome(@RequestBody EditHomeReq editHomeReq, HttpServletRequest request) {
        service.editHome(editHomeReq,request);
    }

    @ApiOperation(value = "查询轮播图片详情", notes = "查询轮播图片详情")
    @RequestMapping(value = "/getHome/{id}", method = RequestMethod.POST)
    public GetHomeRes getHome(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getHome(id,request);
    }

    @ApiOperation(value = "删除轮播图片", notes = "删除轮播图片")
    @RequestMapping(value = "/deleteHome/{id}", method = RequestMethod.POST)
    public void deleteHome(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteHome(id,request);
    }
    
}
