package com.lichi.increaselimit.community.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.utils.UploadUtils;
import com.lichi.increaselimit.common.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(description="文件上传")
@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

	@ApiOperation(value = "文件上传")
	@RequestMapping(value = "upload",method=RequestMethod.POST)
	public ResultVo<Object> upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResultVoUtil.error(1, "文件内容为空");
		}
		// 获取文件名

		String fileName = file.getOriginalFilename();
		log.info("上传的文件名为：" + fileName);
		
		UploadUtils uploadUtils = new UploadUtils();
		String upload = null;
		try {
			upload = uploadUtils.upload(file, fileName);
		} catch (IOException e) {
			throw new BusinessException(ResultEnum.FILE_UPLOAD_ERROR);
		}
		return ResultVoUtil.success(upload);
	}
}