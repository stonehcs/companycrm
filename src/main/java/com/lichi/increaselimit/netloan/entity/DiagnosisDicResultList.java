package com.lichi.increaselimit.netloan.entity;

import java.util.List;

import lombok.Data;

@Data
public class DiagnosisDicResultList {
	
	private List<DiagnosisDicResult> fixed;
	private List<DiagnosisDicResult> percent;
	
}
