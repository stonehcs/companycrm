package com.lichi.increaselimit.netloan.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichi.increaselimit.common.utils.RandomUtils;
import com.lichi.increaselimit.netloan.dao.DiagnosisDicMapper;
import com.lichi.increaselimit.netloan.entity.DiagnosisDic;
import com.lichi.increaselimit.netloan.entity.DiagnosisDicResult;
import com.lichi.increaselimit.netloan.entity.DiagnosisDicResultList;

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

	@Override
	public DiagnosisDicResultList getResult(String bankname, int money) {
		
		DiagnosisDicResultList result = new DiagnosisDicResultList();
		List<DiagnosisDicResult> list = new ArrayList<>();
		List<DiagnosisDicResult> percent = new ArrayList<>();
		
		if ("工商银行".equals(bankname)) {

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 10);
			
			log.info("固定：" + rand);
			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 10);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.05, 0.2, 10, 100, money);
			
			selectByTwo(percent, set,result);

		} else if ("农业银行".equals(bankname)) {

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);
			
			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(5, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);
		} else if ("中国银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);
			
			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			

			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("建设银行".equals(bankname)) {

			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);
			
			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("招商银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 15);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);
		} else if ("中信银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 10);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("交通银行".equals(bankname)) {

			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 10);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("兴业银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("广发银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 10);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("浦发银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(6, 10);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("平安银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 15);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 6);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("华夏银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		} else if ("民生银行".equals(bankname)) {
			

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);
		} else if ("光大银行".equals(bankname)) {

			// 固定
			Integer rand = RandomUtils.generateRandomArray(4, 8);
			log.info("固定：" + rand);

			Set<Integer> random = RandomUtils.generateRandomArray(rand, 10, 500);

			selectByOne(list, random,result);

			// 百分比
			Integer per = RandomUtils.generateRandomArray(2, 8);
			log.info("百分比：" + per);
			
			Set<Integer> set = RandomUtils.generateRandomArray(per, 0.02, 0.15, 10, 100, money);
			selectByTwo(percent, set,result);

		}
		return result;
	}

	/**
	 * 百分比方式
	 * @param map
	 * @param set
	 */
	private void selectByTwo(List<DiagnosisDicResult> list, Set<Integer> set,DiagnosisDicResultList result) {
		for (Integer integer : set) {
			String name = getNameByMoney(integer, 2);
			if(StringUtils.isBlank(name)) {
				continue;
			}
			DiagnosisDicResult diagnosisDicResult = new DiagnosisDicResult();
			diagnosisDicResult.setName(name);
			diagnosisDicResult.setMoney(integer);
			list.add(diagnosisDicResult);
			result.setPercent(list);
		}
	}

	/**
	 * 固定方式
	 * @param map
	 * @param random
	 */
	private void selectByOne(List<DiagnosisDicResult> list, Set<Integer> random,DiagnosisDicResultList result) {
		for (Integer integer : random) {
			String name = getNameByMoney(integer, 1);
			if(StringUtils.isBlank(name)) {
				continue;
			}
			DiagnosisDicResult diagnosisDicResult = new DiagnosisDicResult();
			diagnosisDicResult.setName(name);
			diagnosisDicResult.setMoney(integer);
			list.add(diagnosisDicResult);
			result.setFixed(list);
		}
	}

}
