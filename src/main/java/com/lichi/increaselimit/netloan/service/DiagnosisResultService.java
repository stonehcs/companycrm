package com.lichi.increaselimit.netloan.service;

import java.util.List;

import com.lichi.increaselimit.netloan.entity.DiagnosisResult;
import com.lichi.increaselimit.netloan.entity.DiagnosisResultList;

/**
 * 诊断结果service
 * @author majie
 *
 */
public interface DiagnosisResultService {
	
	/**
	 * 批量新增
	 * @param list
	 */
	void insertList(List<DiagnosisResult> list);

	/**
	 * 通过用户名和银行卡号获得诊断结果
	 * @param userId
	 * @param bankname
	 * @return
	 */
	DiagnosisResultList getResult(String userId, String bankname);

	/**
	 * 更新
	 * @param id
	 * @param money
	 * @return
	 */
	DiagnosisResultList update(Integer id, Double money);
}
