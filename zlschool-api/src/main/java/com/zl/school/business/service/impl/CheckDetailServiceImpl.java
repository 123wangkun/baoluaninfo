package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.check.CheckInfoMapper;
import com.zl.school.business.dao.system.UserMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.entity.check.CheckDetail;
import com.zl.school.business.dao.check.CheckDetailMapper;
import com.zl.school.business.entity.check.CheckInfo;
import com.zl.school.business.entity.system.User;
import com.zl.school.business.service.ICheckDetailService;
import com.zl.school.business.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class CheckDetailServiceImpl extends AbstractServiceImpl<CheckDetail,String> implements ICheckDetailService {

    @Autowired
    private CheckDetailMapper mapper;
    @Autowired
    private CheckInfoMapper checkInfoMapper;
    @Autowired
    private UserMapper userMapper;
    public CheckDetailServiceImpl(CheckDetailMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询考核详情列表
     * @return
     */
    @Override
    public RspPage<GetCheckDetailListRes> getCheckDetailList(GetCheckDetailListReq getCheckDetailListReq, HttpServletRequest request){
        RspPage<GetCheckDetailListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCheckDetailListReq.getPageNum(), getCheckDetailListReq.getPageSize(), true);
        //根据考核id查询所有考核详情列表
        List<GetCheckDetailListRes> orders = mapper.selectCheckDetailByCheckId(getCheckDetailListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询考核详情详情
     * @return
     */
    @Override
    public GetCheckDetailRes getCheckDetail(String id, HttpServletRequest request){
        CheckDetail checkDetail = null;
        GetCheckDetailRes checkDetailRes = null;
        CheckInfo checkInfo = null;
        User user = null;
        //根据考核详情id查询考核详情基本信息
        checkDetail = mapper.selectByPrimaryKey(id);
        if(checkDetail != null){
            checkDetailRes = new GetCheckDetailRes();
            checkDetailRes.setId(checkDetail.getId());
            checkDetailRes.setCheckScore(checkDetail.getCheckScore());
            checkInfo = checkInfoMapper.selectByPrimaryKey(checkDetail.getCheckId());
            checkDetailRes.setName(checkInfo.getName());
            user = userMapper.selectByPrimaryKey(checkDetail.getUserId());
            checkDetailRes.setUserName(user.getRealName());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询考核详情详情失败！");
        }
        return checkDetailRes;
    }

    /**
     * 新增考核详情
     * @return
     */
    @Override
    public void addCheckDetail(AddCheckDetailReq addCheckDetailReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        CheckDetail checkDetail = new CheckDetail();
        checkDetail.setId(UuidUtil.getRandomUuid());
        checkDetail.setEid(eId);
        checkDetail.setCheckId(addCheckDetailReq.getCheckId());
        checkDetail.setUserId(addCheckDetailReq.getUserId());
        checkDetail.setCheckScore(addCheckDetailReq.getCheckScore());
        //添加考核详情记录
        mapper.insertSelective(checkDetail);
    }

    /**
     * 修改考核详情
     * @return
     */
    @Override
    public void editCheckDetail(EditCheckDetailReq editCheckDetailReq, HttpServletRequest request){
        CheckDetail checkDetail = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据考核详情id查询考核详情信息
        checkDetail = mapper.selectByPrimaryKey(editCheckDetailReq.getId());
        if(checkDetail != null){
            checkDetail.setUserId(editCheckDetailReq.getUserId());
            checkDetail.setCheckScore(editCheckDetailReq.getCheckScore());
            //更新考核详情信息
            mapper.updateByPrimaryKeySelective(checkDetail);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改考核详情信息失败！");
        }

    }

    /**
     * 删除考核详情
     * @return
     */
    @Override
    public void deleteCheckDetail(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "考核详情编号不能为空！");
        }
        try {
            //根据id删除考核详情
            mapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }
    
}
