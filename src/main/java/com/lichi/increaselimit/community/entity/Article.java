package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 帖子
 * @author by majie on 2017/11/15.
 */
@Entity
@Table(name =  "t_article")
@Data
@ApiModel("帖子")
public class Article implements Serializable{

	private static final long serialVersionUID = -1997630198932216787L;

	@Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "内容")
    @NotNull(message="内容不能为空")
    @Basic(fetch = FetchType.LAZY) 
    @Column(columnDefinition="TEXT") 
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @NotNull(message = "创建人不能为空")
    private String userId;

    @ApiModelProperty(value = "圈子id")
    @NotNull(message = "圈子id不能为空")
    private Integer circleId;
}
