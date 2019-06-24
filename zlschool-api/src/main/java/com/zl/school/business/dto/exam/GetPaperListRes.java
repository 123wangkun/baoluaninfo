package com.zl.school.business.dto.exam;

import lombok.Data;

@Data
public class GetPaperListRes {

    /**
     * 编码
     */
    private String id;

    /**
     * 企业编码
     */
    private String eid;

    /**
     * 试卷类型
     */
    private String typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 试卷名称
     */
    private String name;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 及格线
     */
    private Integer passLine;

    /**
     * 考试总时长
     */
    private Integer spendTime;

    /**
     * 开始状态(0未开始1已开始2已结束)
     */
    private Integer startStatus;

    /**
     * 发布状态(0未发布1已发布)
     */
    private Integer releaseStatus;

    /**
     * 通过后可以获取的积分数量
     */
    private Integer integral;

    /**
     * 单选题数
     */
    private Integer single;

    /**
     * 多选题数
     */
    private Integer multiple;

    /**
     * 判断题数
     */
    private Integer judge;

    /**
     * 备注
     */
    private String memo;

}
