package com.zl.school.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by huxiangqiang on 2018/5/9.
 */
@Data
public class RspPage<T> {
	@ApiModelProperty(value = "数据")
	List<T> data_list;
	@ApiModelProperty(value = "总记录数")
	Long total_num;
	@ApiModelProperty(value = "当前分页数")
	Integer page_num;
	@ApiModelProperty(value = "分页大小")
	Integer page_size;
	@ApiModelProperty(value = "是否还有下一页")
	Boolean haveNextPage;
}
