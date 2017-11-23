package com.lichi.increaselimit.netloan.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.RandomUtils;
import com.lichi.increaselimit.netloan.dao.DiagnosisDicMapper;
import com.lichi.increaselimit.netloan.dao.DiagnosisMoudleMapper;
import com.lichi.increaselimit.netloan.entity.DiagnosisDic;
import com.lichi.increaselimit.netloan.entity.DiagnosisDicResult;
import com.lichi.increaselimit.netloan.entity.DiagnosisDicResultList;
import com.lichi.increaselimit.netloan.entity.DiagnosisMoudle;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 诊断service
 * 
 * @author majie
 *
 */
@Service
@Slf4j
public class DiagnosisDicServiceImpl implements DiagnosisDicService {

	@Autowired
	private DiagnosisDicMapper diagnosisDicMapper;
	@Autowired
	private DiagnosisMoudleMapper diagnosisMoudleMapper;

	@Override
	public String getNameByMoney(int money, int type) {
		Example example = new Example(DiagnosisDic.class);
		example.createCriteria().andEqualTo("type", type).andCondition("min <= ", money).andCondition("max >= ", money);
		List<DiagnosisDic> list = diagnosisDicMapper.selectByExample(example);
		Collections.shuffle(list);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0).getName();
	}

	/**
	 * 通过银行卡号和类型获得诊断模型
	 * @param bankname
	 * @param type
	 * @return
	 */
	private DiagnosisMoudle getDiagnosisMoudle(String bankname,int type) {
		Example example = new Example(DiagnosisMoudle.class);
		example.createCriteria().andEqualTo("bankname",bankname).andEqualTo("type",type);
		List<DiagnosisMoudle> list = diagnosisMoudleMapper.selectByExample(example);
		if(list.isEmpty()) {
			throw new BusinessException(ResultEnum.NOT_SUPPORT_ERROR);
		}
		return list.get(0);
	}
	
	@Override
	public DiagnosisDicResultList getResult(String bankname, int money) {
		
		DiagnosisDicResultList result = new DiagnosisDicResultList();
		List<DiagnosisDicResult> list = new ArrayList<>();
		List<DiagnosisDicResult> percent = new ArrayList<>();
		
		//固定模式
		DiagnosisMoudle entity = getDiagnosisMoudle(bankname, 1);
		
		// 获取随机次数
		Integer rand = RandomUtils.generateRandomArray(entity.getMinTimes(), entity.getMaxTimes());
		
		log.info("固定次数：" + rand);
		
		//获取随机金额
		Set<Integer> random = RandomUtils.generateRandomArray(rand, entity.getMin(), entity.getMax());
		
		
		getResult(list, random,result,1);
		
		// 百分比
		DiagnosisMoudle entity2 = getDiagnosisMoudle(bankname, 2);
		
		//获取随机次数
		Integer per = RandomUtils.generateRandomArray(entity2.getMinTimes(), entity.getMaxTimes());
		
		log.info("百分比次数：" + per);
		
		//获取百分比金额
		Set<Integer> set = RandomUtils.generateRandomArray(per, entity2.getMinPer(), entity2.getMaxPer(), entity2.getMin(), entity2.getMax(), money);
		
		getResult(percent, set,result,2);
		
		return result;
	}

	/**
	 * 
	 * @param list   固定方式list  or  百分比方式list
	 * @param random		固定方式得到的金额  or 百分比方式得到的金额
	 * @param result		返回的结果
	 * @param type			固定  or  百分比
	 */
	private void getResult(List<DiagnosisDicResult> list, Set<Integer> random,DiagnosisDicResultList result,int type) {
		for (Integer integer : random) {
			String name = getNameByMoney(integer, type);
			if(StringUtils.isBlank(name)) {
				continue;
			}
			DiagnosisDicResult diagnosisDicResult = new DiagnosisDicResult();
			diagnosisDicResult.setName(name);
			diagnosisDicResult.setMoney(integer);
			list.add(diagnosisDicResult);
			
			if(1 == type) {
				result.setFixed(list);
			}else {
				result.setPercent(list);
			}
		}
	}

}
