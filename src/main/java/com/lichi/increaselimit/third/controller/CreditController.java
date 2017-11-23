package com.lichi.increaselimit.third.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.utils.MD5Utils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 征信报告
 * 
 * @author majie
 *
 */
@RestController
@RequestMapping("/credit")
@Slf4j
public class CreditController {

	private String userid = "";

	@Autowired
	private RestTemplate restTemplate;

	private final static String uid = "900024";
	private final static String ukey = "b2abd726e314c0978";
	private final static String success_url = "http://mengcan.vicp.io/credit/getUserId";
	private final static String error_url = "http://mengcan.vicp.io/credit/getUserId/erro";

	// 获取userId
	private final static String getForUserId = "http://cc.intecredit.cn/creditreport.html";

	@GetMapping
	public ResultVo<String> getUserId() {
		long ctm = System.currentTimeMillis();
		String token = MD5Utils.getResult((ukey + ctm));
		String result = getForUserId + "?uid=" + uid + "&ctm=" + ctm + "&token=" + token + "&Surl="
		+ success_url + "&Eurl" + error_url;
		
//		System.out.println(a);
//		String result = restTemplate.getForObject(getForUserId + "?uid=" + uid + "&ctm=" + ctm + "&token=" + token + "&Surl="
//				+ success_url + "&Eurl" + error_url, String.class);
//		return null;
		
		return ResultVoUtil.success(result);
	}

	@GetMapping("/getUserId")
	public void getUserId(HttpServletRequest request) {
		userid = request.getParameter("userid");
		log.info("userid:{}", userid);
	}

	@GetMapping("/getUserId/erro")
	public void getUserIdError(HttpServletRequest request) {
		log.info("错误");
	}

}
