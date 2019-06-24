package com.zl.school.business.controller.message;

import com.zl.school.business.common.exception.ExceptionController;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.service.IMessageService;
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
@RequestMapping("/message")
@Api(value = "知识管理后台API", description = "发布管理", tags = {"MessageController"})
public class MessageController extends ExceptionController {
    private static final Logger LOG = Logger.getLogger(ExceptionController.class);
    @Autowired
    private IMessageService service;


    @ApiOperation(value = "查询消息列表", notes = "查询消息列表")
    @ApiImplicitParam(name = "getMessageListReq", value = "查询消息列表", required = true, dataType = "GetMessageListReq")
    @RequestMapping(value = "/getMessageList", method = RequestMethod.POST)
    public RspPage<GetMessageListRes> getMessageList(@RequestBody GetMessageListReq getMessageListReq, HttpServletRequest request) {
        return service.getMessageList(getMessageListReq,request);
    }

    @ApiOperation(value = "新增消息", notes = "新增消息")
    @ApiImplicitParam(name = "addMessageReq", value = "新增消息", required = true, dataType = "AddMessageReq")
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public void addMessage(@RequestBody AddMessageReq addMessageReq, HttpServletRequest request) {
        service.addMessage(addMessageReq,request);
    }

    @ApiOperation(value = "修改消息", notes = "修改消息")
    @ApiImplicitParam(name = "editMessageReq", value = "修改消息", required = true, dataType = "EditMessageReq")
    @RequestMapping(value = "/editMessage", method = RequestMethod.POST)
    public void editMessage(@RequestBody EditMessageReq editMessageReq, HttpServletRequest request) {
        service.editMessage(editMessageReq,request);
    }

    @ApiOperation(value = "查询消息详情", notes = "查询消息详情")
    @RequestMapping(value = "/getMessage/{id}", method = RequestMethod.POST)
    public GetMessageRes getMessage(@PathVariable("id") String id, HttpServletRequest request) {
        return service.getMessage(id,request);
    }

    @ApiOperation(value = "删除消息", notes = "删除消息")
    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.POST)
    public void deleteMessage(@PathVariable("id") String id, HttpServletRequest request) {
        service.deleteMessage(id,request);
    }
}
