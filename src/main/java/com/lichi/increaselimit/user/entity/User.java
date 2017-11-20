package com.lichi.increaselimit.user.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.lichi.increaselimit.community.entity.Circle;

import lombok.Data;

/**
 * 用户信息
 * @author majie
 *
 */
@Entity
@Table(name = "t_user")
@Data
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String headImg;
	
	private String password;
	
	private String mobilephone;
	
	private String qq;
	
	private String weixin;
	
	private Integer vipLevel;
	
    @ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    @JoinTable(name = "t_user_circle",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	private Set<Circle> circles;
	
	private BigDecimal money;
	
}
