package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 帖子
 * @author by majie on 2017/11/15.
 */
@Data
public class Article implements Serializable{

	private static final long serialVersionUID = -1997630198932216787L;

    private Integer id;

    private String title;

    private String content;
    
    private Date createTime;

    private Date updateTime;

    private Integer createUserId;

    private Integer circleId;
}
