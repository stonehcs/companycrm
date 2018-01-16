package com.lichi.increaselimit.order.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderUpdateDto {
	
	@ApiModelProperty("订单id")
	@NotNull(message = "订单id不能为空")
	private String id;
	
	private String userId;
	
	private Integer goodsId;
	
	private Integer type;
	
	private Integer status;
	
	private Double money;
	
	private Date updateTime;
	
	
}
