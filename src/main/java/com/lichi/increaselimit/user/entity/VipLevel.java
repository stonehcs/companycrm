package com.lichi.increaselimit.user.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息
 * @author majie
 *
 */
@Data
@Table(name = "t_vip_level")
public class VipLevel implements Serializable{
	
	private static final long serialVersionUID = 9196847346716343169L;
	
	@Id
	private Integer id;
	
	@ApiModelProperty("用户等级")
	private Integer level;
	
	@ApiModelProperty("等级名称")
	private String levelName;
	
	@ApiModelProperty("对应的金额")
	private Double levelMoney; 
	
}
