package com.zl.school.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.school.business.dto.RspPage;
import com.zl.school.business.dto.exam.GetExamOnlineUserReq;
import com.zl.school.business.dto.exam.GetExamOnlineUserRes;
import com.zl.school.business.dto.exam.GetExamUnderUserRes;
import com.zl.school.business.entity.exam.ExamOnline;
import com.zl.school.business.dao.exam.ExamOnlineMapper;
import com.zl.school.business.service.IExamOnlineService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public class ExamOnlineServiceImpl extends AbstractServiceImpl<ExamOnline,String> implements IExamOnlineService {

    @Autowired
    private ExamOnlineMapper mapper;

    public ExamOnlineServiceImpl(ExamOnlineMapper mapper) {
        super(mapper);
        this.mapper = mapper;
    }

    /**
     * 查询线上考试已考用户列表
     * @return
     */
    @Override
    public RspPage<GetExamOnlineUserRes> getExamOnlineUser(GetExamOnlineUserReq req, HttpServletRequest request){
        RspPage<GetExamOnlineUserRes> rspPage = new RspPage<>();
        //初始化分页
        Page page = PageHelper.startPage(req.getPageNum(), req.getPageSize(), true);
        //根据线下考试编号查询已考用户列表
        List<GetExamOnlineUserRes> orders = mapper.selectExamOnlineUserList(req);
        rspPage.setData_list(orders);
        rspPage.setTotal_num(page.getTotal());
        rspPage.setPage_num(page.getPageNum());
        rspPage.setPage_size(page.getPageSize());
        return rspPage;
    }

}
