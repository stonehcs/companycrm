package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * 圈子
 * 
 * @author by majie on 2017/11/16.
 */
@Data
@Entity
@Table(name = "t_circle")
public class Circle implements Serializable {

	private static final long serialVersionUID = 8344002416206506566L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String createUserId;

	private Date createTime;

	private Date updateTime;

	private String url;

	@Transient
	private Integer count;
	
//    @ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
//    @JoinTable(name = "t_user_circle",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
//    private Set<User> users;
}
