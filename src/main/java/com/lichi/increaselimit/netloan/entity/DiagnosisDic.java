package com.lichi.increaselimit.netloan.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name ="t_diagnosis_dic")
@Data
public class DiagnosisDic implements Serializable{

	private static final long serialVersionUID = -3603548829861336843L;
	
	@Id
	private Integer id;
	
	private Integer type;
	
	private Integer min;
	
	private Integer max;
	
	private String name;
	
}
