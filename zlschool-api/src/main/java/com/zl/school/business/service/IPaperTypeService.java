package com.zl.school.business.service;

import com.zl.school.business.dto.exam.AddPaperTypeReq;
import com.zl.school.business.dto.exam.EditPaperTypeReq;
import com.zl.school.business.dto.exam.GetPaperTypeListRes;
import com.zl.school.business.dto.exam.GetPaperTypeRes;
import com.zl.school.business.entity.exam.PaperType;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IPaperTypeService extends IBaseService<PaperType,String> {


    /**
     * 查询试卷类型列表
     * @return
     */
    List<GetPaperTypeListRes> getPaperTypeList(HttpServletRequest request);

    /**
     * 查询试卷类型详情
     * @return
     */
    GetPaperTypeRes getPaperType(String id, HttpServletRequest request);

    /**
     * 新增试卷类型
     * @return
     */
    void addPaperType(AddPaperTypeReq addPaperTypeReq, HttpServletRequest request);

    /**
     * 修改试卷类型
     * @return
     */
    void editPaperType(EditPaperTypeReq editPaperTypeReq, HttpServletRequest request);

    /**
     * 删除试卷类型
     * @return
     */
    void deletePaperType(String id, HttpServletRequest request);
    
}
