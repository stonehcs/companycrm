package com.lichi.increaselimit.netloan.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 诊断模型
 * @author majie
 *
 */
@Table(name ="t_diagnosis_moudle")
@Data
public class DiagnosisMoudle implements Serializable{

	private static final long serialVersionUID = -9039863723678937325L;

	@Id
	private Integer id;
	
	private Integer type;
	
	private Integer min;
	
	private Integer max;
	
	private String bankname;
	
	private Double minPer;
	
	private Double maxPer;
	
	private Integer minTimes;
	
	private Integer maxTimes;
	
	
}
