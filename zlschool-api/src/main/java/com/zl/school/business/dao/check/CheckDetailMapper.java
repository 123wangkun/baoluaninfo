package com.zl.school.business.dao.check;

import com.zl.school.business.dto.check.GetCheckDetailListReq;
import com.zl.school.business.dto.check.GetCheckDetailListRes;
import com.zl.school.business.entity.check.CheckDetail;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface CheckDetailMapper extends BaseDao<CheckDetail,String> {

    /**
     * 根据考核id查询统计总记录数
     * @return
     */
    int selectCountCheck(String checkId);
    /**
     * 根据考核id查询参加用户列表
     * @return
     */
    List<GetCheckDetailListRes> selectCheckDetailByCheckId(GetCheckDetailListReq getCheckDetailListReq);
}
