package com.zl.school.business.entity.train;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 岗位培训资料
 *
 * @author 南京深卡网络技术有限公司
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_train_file")
public class TrainFile implements Serializable {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 资料名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer position;

    /**
     * 关联培训id
     */
    private String trainId;

    /**
     * 文件类型(1视频,2PDF,3EXCEL,4WORD)
     */
    private Integer fileType;

    /**
     * 文件路径
     */
    private String fileSrc;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 更新人ID
     */
    private String updateUser;


}
