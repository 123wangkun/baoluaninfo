package com.zl.school.business.service;

import com.zl.school.business.entity.system.Param;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface IParamService extends IBaseService<Param,String> {
    /**
     * 查询发送短信相关配置
     * @return
     */
    String selectByName(String name);
}
