package com.zl.school.business.dao.message;

import com.zl.school.business.dto.message.GetMessageListReq;
import com.zl.school.business.dto.message.GetMessageListRes;
import com.zl.school.business.entity.message.Message;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface MessageMapper extends BaseDao<Message,String> {
    /**
     * 查询企业下的所有消息
     * @return
     */
    List<GetMessageListRes> selectAllMessageByEid(GetMessageListReq getCerTypeListReq);
}
