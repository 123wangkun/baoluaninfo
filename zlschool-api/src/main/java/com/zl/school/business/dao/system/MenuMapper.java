package com.zl.school.business.dao.system;

import com.zl.school.business.entity.system.Menu;
import com.zl.school.business.dao.BaseDao;

import java.util.List;

/**
 * @author 南京深卡网络技术有限公司
 */
public interface MenuMapper extends BaseDao<Menu,String> {
    /**
     * 查询所有的菜单列表
     * @return
     */
    List<Menu> selectAllMenu();

    /**
     * 查询所有的菜单列表
     * @return
     */
    List<Menu> selectAllMenuByUser(String user,String eId);

}
