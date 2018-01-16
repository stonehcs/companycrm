package com.lichi.increaselimit.order.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "t_order")
public class Order {
	
	@Id
	private String id;
	
	private String userId;
	
	private Integer goodsId;
	
	private Integer type;
	
	private Integer status;
	
	private Double money;
	
	@OrderBy("desc")
	private Date createTime;
	
	private Date updateTime;
	
	
}
