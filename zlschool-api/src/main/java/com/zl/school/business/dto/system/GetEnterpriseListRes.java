package com.zl.school.business.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class GetEnterpriseListRes {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业编码
     */
    private String code;

    /**
     * 允许上传文件大小(默认2048M)
     */
    private Long allowSize;

    /**
     * 已上传文件大小
     */
    private Long makeSize;

    /**
     * 允许使用的用户数(默认10000)
     */
    private Integer allowUser;

    /**
     * 已有用户数
     */
    private Integer makeUser;

    /**
     * 该企业有效期(默认一年)
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date validate;

    /**
     * 状态1:启用 2:禁用
     */
    private Integer status;

    /**
     * 状态1:待审核 2:审核不通过 3:审核已通过
     */
    private Integer proStatus;

    /**
     * 状态1:待审核 2:审核不通过 3:启用 4:禁用
     */
    private String statusName;

    /**
     * 营业执照URL地址
     */
    private String license;

    /**
     * 营业执照有效期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date licenseTerm;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

}
