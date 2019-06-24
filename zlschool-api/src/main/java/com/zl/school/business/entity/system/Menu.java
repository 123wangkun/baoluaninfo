package com.zl.school.business.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_menu")
public class Menu implements Serializable {

    /**
     * 菜单名称
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 上级编码
     */
    private String parentId;

    /**
     * 连接路径
     */
    private String linkUrl;

    /**
     * 图标路径
     */
    private String picUrl;

    /**
     * 类型 0菜单 1按钮
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer position;

    /**
     * 描述
     */
    private String memo;

    /**
     * 描述
     */
    private String note;


}
