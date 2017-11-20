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

import lombok.Data;

/**
 * 帖子
 * @author by majie on 2017/11/15.
 */
@Entity
@Table(name =  "t_article")
@Data
public class Article implements Serializable{

	private static final long serialVersionUID = -1997630198932216787L;

	@Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Basic(fetch = FetchType.LAZY) 
    @Column(columnDefinition="TEXT") 
    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer createUserId;

    private Integer circleId;
}
