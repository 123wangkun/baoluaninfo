package com.zl.school.business.service;

import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.entity.message.Home;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IHomeService extends IBaseService<Home,String> {


    /**
     * 查询轮播图片列表
     * @return
     */
    RspPage<GetHomeListRes> getHomeList(GetHomeListReq getHomeListReq, HttpServletRequest request);

    /**
     * 查询轮播图片详情
     * @return
     */
    GetHomeRes getHome(String id, HttpServletRequest request);

    /**
     * 新增轮播图片
     * @return
     */
    void addHome(AddHomeReq addHomeReq, HttpServletRequest request);

    /**
     * 修改轮播图片
     * @return
     */
    void editHome(EditHomeReq editHomeReq, HttpServletRequest request);

    /**
     * 删除轮播图片
     * @return
     */
    void deleteHome(String id, HttpServletRequest request);
    
}
