package com.zl.school.business.dao.system;

import com.zl.school.business.dao.BaseDao;
import com.zl.school.business.entity.system.UserPower;

import java.util.List;

/**
 * @author MrBird
 */
public interface UserPowerMapper extends BaseDao<UserPower,String> {

    /**
     * 统计该企业下的该用户总共有多少个角色
     * @return
     */
    Integer selectCount(String eid,String userId);

    /**
     * 根据企业id  用户id删除记录
     * @return
     */
    void deleteRoles(String eid,String userId);

    /**
     * 根据企业id  用户id查询所拥有的角色
     * @return
     */
    List<UserPower> selectByKey(UserPower userPower);

}
