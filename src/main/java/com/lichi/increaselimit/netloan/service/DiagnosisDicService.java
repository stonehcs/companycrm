package com.lichi.increaselimit.netloan.service;

import com.lichi.increaselimit.netloan.entity.DiagnosisDicResultList;

public interface DiagnosisDicService {


	String getNameByMoney(int money, int type);
	
	DiagnosisDicResultList getResult(String bankname, int money);
}
