package com.lichi.increaselimit.netloan.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 诊断结果
 * @author majie
 *
 */
@Data
public class DiagnosisDicResult implements Serializable{

	private static final long serialVersionUID = 5964883708812056459L;

	private String name;
	
	private Integer money;
}
