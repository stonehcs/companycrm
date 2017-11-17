package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 圈子
 * @author by majie on 2017/11/16.
 */
@Data
@Entity
@Table(name = "t_circle")
@ApiModel("圈子 ")
public class Circle implements Serializable{

	private static final long serialVersionUID = 8344002416206506566L;

	@Id
    @GeneratedValue
    @NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "圈子名称")
    @NotNull(message = "圈子名字不能为空")
    private String name;

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userId;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "头像地址")
    @NotNull(message = "头像不能为空")
    private String url;

    @Transient
    @ApiModelProperty(value = "主题数")
    private Integer count;
}
