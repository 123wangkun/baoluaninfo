package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.common.exception.BusinessException;
import com.zl.school.business.common.exception.ErrorCode;
import com.zl.school.business.dao.check.CheckDetailMapper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.check.*;
import com.zl.school.business.entity.check.CheckInfo;
import com.zl.school.business.dao.check.CheckInfoMapper;
import com.zl.school.business.service.ICheckInfoService;
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
public class CheckInfoServiceImpl extends AbstractServiceImpl<CheckInfo,String> implements ICheckInfoService {

    @Autowired
    private CheckInfoMapper mapper;
    @Autowired
    private CheckDetailMapper checkDetailMapper;
    public CheckInfoServiceImpl(CheckInfoMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询考核列表
     * @return
     */
    @Override
    public RspPage<GetCheckInfoListRes> getCheckInfoList(GetCheckInfoListReq getCheckInfoListReq, HttpServletRequest request){
        //获取当前用户的企业id
        HttpSession session = request.getSession();
        String eId = (String) session.getAttribute("eId");
        getCheckInfoListReq.setEId(eId);
        RspPage<GetCheckInfoListRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(getCheckInfoListReq.getPageNum(), getCheckInfoListReq.getPageSize(), true);
        //根据企业id查询所有考核列表
        List<GetCheckInfoListRes> orders = mapper.selectAllCheckInfoByEid(getCheckInfoListReq);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

    /**
     * 查询考核详情
     * @return
     */
    @Override
    public GetCheckInfoRes getCheckInfo(String id, HttpServletRequest request){
        CheckInfo checkInfo = null;
        GetCheckInfoRes checkInfoRes = null;
        //根据考核id查询考核基本信息
        checkInfo = mapper.selectByPrimaryKey(id);
        if(checkInfo != null){
            checkInfoRes = new GetCheckInfoRes();
            checkInfoRes.setId(checkInfo.getId());
            checkInfoRes.setContent(checkInfo.getContent());
            checkInfoRes.setName(checkInfo.getName());
            checkInfoRes.setCheckTime(checkInfo.getCheckTime());
            checkInfoRes.setMemo(checkInfo.getMemo());
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "查询考核详情失败！");
        }
        return checkInfoRes;
    }

    /**
     * 新增考核
     * @return
     */
    @Override
    public void addCheckInfo(AddCheckInfoReq addCheckInfoReq, HttpServletRequest request){
        try{
            //获取当前用户的企业id
            HttpSession session = request.getSession();
            String eId = (String) session.getAttribute("eId");
            String userId = (String) session.getAttribute("userId");
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setId(UuidUtil.getRandomUuid());
            checkInfo.setEid(eId);
            checkInfo.setContent(addCheckInfoReq.getContent());
            checkInfo.setName(addCheckInfoReq.getName());
            checkInfo.setCheckTime(addCheckInfoReq.getCheckTime());
            checkInfo.setMemo(addCheckInfoReq.getMemo());
            checkInfo.setCreateTime(new Date());
            checkInfo.setCreateUser(userId);
            //添加考核记录
            mapper.insertSelective(checkInfo);
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }

    /**
     * 修改考核
     * @return
     */
    @Override
    public void editCheckInfo(EditCheckInfoReq editCheckInfoReq, HttpServletRequest request){
        CheckInfo checkInfo = null;
        //获取当前用户的id
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String eId = (String) session.getAttribute("eId");
        //根据考核id查询考核信息
        checkInfo = mapper.selectByPrimaryKey(editCheckInfoReq.getId());
        if(checkInfo != null){
            checkInfo.setContent(editCheckInfoReq.getContent());
            checkInfo.setName(editCheckInfoReq.getName());
            checkInfo.setCheckTime(editCheckInfoReq.getCheckTime());
            checkInfo.setMemo(editCheckInfoReq.getMemo());
            checkInfo.setCreateTime(new Date());
            checkInfo.setCreateUser(userId);
            checkInfo.setUpdateTime(new Date());
            checkInfo.setUpdateUser(userId);
            //更新考核信息
            mapper.updateByPrimaryKeySelective(checkInfo);
        }else{
            throw new BusinessException(ErrorCode.UPDATE_DATA_FAIL, "修改考核信息失败！");
        }

    }

    /**
     * 删除考核
     * @return
     */
    @Override
    public void deleteCheckInfo(String id, HttpServletRequest request){
        if(id.isEmpty()){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "考核编号不能为空！");
        }
        try {
            //根据类型id查询该考核是否已关联用户
            int size = checkDetailMapper.selectCountCheck(id);
            if (size > 0) {
                throw new BusinessException(ErrorCode.ERROR_PHONE, "已有用户参与了考核，不能删除！");
            } else {
                //根据id删除考核
                mapper.deleteByPrimaryKey(id);
            }
        }catch (Exception e){
            throw new BusinessException(ErrorCode.ERROR_PHONE, "删除失败！");
        }
    }
    
}
