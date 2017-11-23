package com.lichi.increaselimit.course.controller.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 讲师
 * @author majie
 *
 */
@Data
public class TeacherDto implements Serializable{

	private static final long serialVersionUID = 3308075062590670824L;
	
	@Id
	private Integer id;
	
	@ApiModelProperty("老师姓名")
	@NotNull(message = "老师姓名不能为空")
	private String teachername;
	
	@ApiModelProperty("老师简介")
	private String introduce;
	
	@ApiModelProperty("讲师头像")
	private String imgUrl;
	
}
