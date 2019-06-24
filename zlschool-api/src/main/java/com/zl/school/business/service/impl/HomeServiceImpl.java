package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.message.*;
import com.zl.school.business.entity.message.Home;
import com.zl.school.business.dao.message.HomeMapper;
import com.zl.school.business.service.IHomeService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class HomeServiceImpl extends AbstractServiceImpl<Home,String> implements IHomeService {

    @Autowired
    private HomeMapper mapper;

    public HomeServiceImpl(HomeMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询轮播图片列表
     * @return
     */
    @Override
    public RspPage<GetHomeListRes> getHomeList(GetHomeListReq getHomeListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        RspPage<GetHomeListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getHomeListReq.getPageNum(), getHomeListReq.getPageSize(), true);
        //根据轮播图片类型以及企业id查询所有轮播图片列表
        List<GetHomeListRes> orders = mapper.selectAllHomeByEid(eId);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询轮播图片详情
     * @return
     */
    @Override
    public GetHomeRes getHome(String id, HttpServletRequest request){
        Home home = null;
        GetHomeRes homeRes = null;
        //根据轮播图片id查询轮播图片基本信息
        home = mapper.selectByPrimaryKey(id);
        if(home != null){
            homeRes = new GetHomeRes();
            homeRes.setId(home.getId());
            homeRes.setName(home.getName());
            homeRes.setMemo(home.getMemo());
            homeRes.setState(home.getState());
            homeRes.setSrc(home.getSrc());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询轮播图片详情失败！");
        }
        return homeRes;
    }

    /**
     * 新增轮播图片
     * @return
     */
    @Override
    public void addHome(AddHomeReq addHomeReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        String userId = (String) session.getAttribute("userId");
        Home home = new Home();
        home.setId(UuidUtil.getRandomUuid());
        home.setEid(eId);
        home.setName(addHomeReq.getName());
        home.setMemo(addHomeReq.getMemo());
        home.setState(addHomeReq.getState());
        home.setSrc(addHomeReq.getSrc());
        home.setCreatedTime(new Date());
        home.setCreateUser(userId);
        mapper.insertSelective(home);
    }

    /**
     * 修改轮播图片
     * @return
     */
    @Override
    public void editHome(EditHomeReq editHomeReq, HttpServletRequest request){
        Home home = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        //根据轮播图片id查询轮播图片信息
        home = mapper.selectByPrimaryKey(editHomeReq.getId());
        if(home != null){
            home.setId(home.getId());
            home.setName(editHomeReq.getName());
            home.setMemo(editHomeReq.getMemo());
            home.setSrc(editHomeReq.getSrc());
            home.setState(editHomeReq.getState());
            home.setUpdatedTime(new Date());
            home.setUpdateUser(userId);
            //更新轮播图片信息
            mapper.updateByPrimaryKeySelective(home);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改轮播图片信息失败！");
        }

    }

    /**
     * 删除轮播图片
     * @return
     */
    @Override
    public void deleteHome(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "轮播图片编号不能为空！");
        }
        //根据id删除轮播图片
        mapper.deleteByPrimaryKey(id);
    }
    
}
