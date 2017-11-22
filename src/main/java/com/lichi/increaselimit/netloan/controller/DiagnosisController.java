package com.lichi.increaselimit.netloan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.netloan.entity.DiagnosisDicResultList;
import com.lichi.increaselimit.netloan.service.DiagnosisDicService;

import io.swagger.annotations.Api;

/**
 * 诊断
 * @author majie
 *
 */
@RestController
@RequestMapping("/diagnosis")
@Api(description = "诊断")
public class DiagnosisController {
	
	@Autowired
	private DiagnosisDicService diagnosisDicService;
	
	@GetMapping
	public ResultVo<DiagnosisDicResultList> diagnosis(@RequestParam String bankname,@RequestParam int money) {
		DiagnosisDicResultList result = diagnosisDicService.getResult(bankname, money);
		return ResultVoUtil.success(result);
	}
}
