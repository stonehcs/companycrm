package com.lichi.increaselimit.community.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 圈子dto
 * @author by majie on 2017/11/16.
 */
@Data
@ApiModel("圈子 ")
public class CircleDto implements Serializable{

	private static final long serialVersionUID = 8344002416206506566L;
	
	@ApiModelProperty(value = "圈子id插入的时候不用传")
    private Integer id;

    @ApiModelProperty(value = "圈子名称")
    @NotNull(message = "圈子名字不能为空")
    private String name;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    private String createUserId;

    @ApiModelProperty(value = "头像地址")
    @NotNull(message = "头像不能为空")
    private String url;
    
    @ApiModelProperty(value = "所有圈子排序，可以为空")
    private Integer sort1;
    
    @ApiModelProperty(value = "热门圈子排序，可以为空")
    private Integer sort2;

}
