package com.zl.school.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExceptionLog {
    private Integer id;

    private String param;

    private Date createTime;

    private String actionKey;

    private String ip;

    private Integer port;

    private String exceptionInfo;
}