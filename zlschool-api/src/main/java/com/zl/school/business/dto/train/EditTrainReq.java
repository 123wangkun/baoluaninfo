package com.zl.school.business.dto.train;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EditTrainReq {

    @ApiModelProperty(value = "编码",required = true)
    private String id;

    @ApiModelProperty(value = "显示顺序",required = true)
    private Integer position;

    @ApiModelProperty(value = "培训名称",required = true)
    private String name;

    @ApiModelProperty(value = "开始时间",required = true)
    private Date startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    private Date endTime;

    @ApiModelProperty(value = "状态(1未开始2进行中3已结束4禁用)",required = true)
    private Integer status;

    @ApiModelProperty(value = "培训所得积分",required = true)
    private Integer integral;

    @ApiModelProperty(value = "标签列表",required = true)
    private List<EditTrainLabelList> labelIds;
    @Data
    public static class EditTrainLabelList{
        @ApiModelProperty(value = "标签ID",required = true)
        private String labelId;
    }

}
