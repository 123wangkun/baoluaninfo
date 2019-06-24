package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.entity.message.Message;
import com.zl.school.business.dao.message.MessageMapper;
import com.zl.school.business.service.IMessageService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class MessageServiceImpl extends AbstractServiceImpl<Message,String> implements IMessageService {

    @Autowired
    private MessageMapper mapper;

    public MessageServiceImpl(MessageMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询消息列表
     * @return
     */
    @Override
    public RspPage<GetMessageListRes> getMessageList(GetMessageListReq getMessageListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getMessageListReq.setEId(eId);
        RspPage<GetMessageListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getMessageListReq.getPageNum(), getMessageListReq.getPageSize(), true);
        //根据消息类型以及企业id查询所有消息列表
        List<GetMessageListRes> orders = mapper.selectAllMessageByEid(getMessageListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询消息详情
     * @return
     */
    @Override
    public GetMessageRes getMessage(String id, HttpServletRequest request){
        Message message = null;
        GetMessageRes MessageRes = null;
        //根据消息id查询消息基本信息
        message = mapper.selectByPrimaryKey(id);
        if(message != null){
            MessageRes = new GetMessageRes();
            MessageRes.setId(message.getId());
            MessageRes.setClassname(message.getClassname());
            MessageRes.setContent(message.getContent());
            MessageRes.setDescribe(message.getDescribe());
            MessageRes.setState(message.getState());
            MessageRes.setSrc(message.getSrc());
            MessageRes.setTitle(message.getTitle());
            MessageRes.setType(message.getType());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询消息详情失败！");
        }
        return MessageRes;
    }

    /**
     * 新增消息
     * @return
     */
    @Override
    public void addMessage(AddMessageReq addMessageReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Message message = new Message();
        message.setId(UuidUtil.getRandomUuid());
        message.setEid(eId);
        message.setClassname(addMessageReq.getClassname());
        message.setContent(addMessageReq.getContent());
        message.setDescribe(addMessageReq.getDescribe());
        message.setState(addMessageReq.getState());
        message.setTitle(addMessageReq.getTitle());
        message.setSrc(addMessageReq.getSrc());
        message.setType(addMessageReq.getType());
        message.setCreatedTime(new Date());
        message.setCreateUser(userId);
        mapper.insertSelective(message);
    }

    /**
     * 修改消息
     * @return
     */
    @Override
    public void editMessage(EditMessageReq editMessageReq, HttpServletRequest request){
        Message message = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据消息id查询消息信息
        message = mapper.selectByPrimaryKey(editMessageReq.getId());
        if(message != null){
            message.setClassname(editMessageReq.getClassname());
            message.setContent(editMessageReq.getContent());
            message.setDescribe(editMessageReq.getDescribe());
            message.setTitle(editMessageReq.getTitle());
            message.setSrc(editMessageReq.getSrc());
            message.setState(editMessageReq.getState());
            message.setUpdatedTime(new Date());
            message.setUpdateUser(userId);
            //更新消息信息
            mapper.updateByPrimaryKeySelective(message);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改消息信息失败！");
        }

    }

    /**
     * 删除消息
     * @return
     */
    @Override
    public void deleteMessage(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "消息编号不能为空！");
        }
        //根据id删除消息
        mapper.deleteByPrimaryKey(id);
    }
}
