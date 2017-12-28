package com.lichi.increaselimit.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.FileUtil;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.VersionDto;
import com.lichi.increaselimit.sys.entity.Version;
import com.lichi.increaselimit.sys.service.VersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(description = "版本信息")
@RestController
@RequestMapping("/version")
@Slf4j
public class VersionController {

	@Autowired
	private VersionService versionService;

	@GetMapping
	@ApiOperation("分页查询所有版本")
	public ResultVo<PageInfo<Version>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("分页查询所有版本");
		PageInfo<Version> list = versionService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}
	@GetMapping("/list")
	@ApiOperation("查询所有版本")
	public ResultVo<List<Version>> getAll() {
		log.info("查询所有版本");
		List<Version> list = versionService.selectList();
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加版本信息")
	public ResultVo<Version> add(@Valid @RequestBody VersionDto deptDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("添加版本信息参数错误:{}" + errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("添加版本,版本号:{}",deptDto.getVersion());
		Version dept = new Version();
		BeanUtils.copyProperties(deptDto, dept);
		versionService.insertOne(dept);
		return ResultVoUtil.success();
	}


	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除版本信息")
	public ResultVo<Version> delete(@PathVariable Integer id) {
		log.info("删除版本信息,版本id:{}",id);
		versionService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询版本信息")
	public ResultVo<Version> get(@PathVariable Integer id) {
		log.info("查询版本信息,版本id:{}",id);
		Version list = versionService.selectOne(id);
		return ResultVoUtil.success(list);
	}
	
	
	@ApiOperation(value = "文件上传")
	@RequestMapping(value = "/upload",method=RequestMethod.POST)
	public ResultVo<Object> upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResultVoUtil.error(1, "文件内容为空");
		}
		// 获取文件名

		String fileName = file.getOriginalFilename();
		log.info("上传的文件名为：" + fileName);
		
		try {
			FileUtil.uploadFile(file.getBytes(), "/home/homepage/release", fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ResultVoUtil.success();
	}
}
