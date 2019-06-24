package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.entity.message.Message;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IMessageService extends IBaseService<Message,String> {

    /**
     * 查询消息列表
     * @return
     */
    RspPage<GetMessageListRes> getMessageList(GetMessageListReq getMessageListReq, HttpServletRequest request);

    /**
     * 查询消息详情
     * @return
     */
    GetMessageRes getMessage(String id, HttpServletRequest request);

    /**
     * 新增消息
     * @return
     */
    void addMessage(AddMessageReq addMessageReq, HttpServletRequest request);

    /**
     * 修改消息
     * @return
     */
    void editMessage(EditMessageReq editMessageReq, HttpServletRequest request);

    /**
     * 删除消息
     * @return
     */
    void deleteMessage(String id, HttpServletRequest request);
}
