package com.lichi.increaselimit.community.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Article新增的时候数据传输对象
 * 
 * @author majie
 *
 */
@Data
@ApiModel("帖子")
public class ArticleDto implements Serializable {

	private static final long serialVersionUID = -1997630198932216787L;

	@ApiModelProperty(value = "标题")
	@NotBlank(message = "标题不能为空")
	private String title;

	@ApiModelProperty(value = "内容")
	@NotBlank(message = "内容不能为空")
	private String content;

	@ApiModelProperty(value = "圈子id")
	@NotNull(message = "圈子id不能为空")
	private Integer circleId;
	
	@ApiModelProperty(value = "帖子图片")
	@NotBlank(message = "帖子图片不能为空")
	private String articleImg;
	
	@ApiModelProperty(value = "热门帖子排序")
	private Integer sort;
}
