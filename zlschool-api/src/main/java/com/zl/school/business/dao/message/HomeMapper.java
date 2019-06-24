package com.zl.school.business.dao.message;

import com.zl.school.business.dto.message.GetHomeListReq;
import com.zl.school.business.dto.message.GetHomeListRes;
import com.zl.school.business.entity.message.Home;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface HomeMapper extends BaseDao<Home,String> {
    /**
     * 查询企业下的所有轮播图片
     * @return
     */
    List<GetHomeListRes> selectAllHomeByEid(String eId);


}
