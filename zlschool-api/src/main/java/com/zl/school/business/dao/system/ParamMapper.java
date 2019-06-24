package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.system.Param;
import org.springframework.stereotype.Service;

/**
 * @author 南京深卡网络技术有限公司
 */
@Service
public interface ParamMapper extends BaseDao<Param,String> {

    /**
     * 查询发送短信相关配置
     * @return
     */
    String selectByName(String name);

}
