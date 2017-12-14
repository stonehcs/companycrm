package com.lichi.increaselimit.course.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 讲师
 * @author majie
 *
 */
@Data
public class TeacherVo implements Serializable{

	private static final long serialVersionUID = -5100502531718331318L;

	private Integer id;
	
	private String teachername;
	
}
