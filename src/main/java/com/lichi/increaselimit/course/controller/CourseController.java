package com.lichi.increaselimit.course.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.course.controller.dto.CourseDto;
import com.lichi.increaselimit.course.controller.dto.CourseUpdateDto;
import com.lichi.increaselimit.course.entity.Course;
import com.lichi.increaselimit.course.entity.CourseVo;
import com.lichi.increaselimit.course.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 课程controller
 * 
 * @author majie
 *
 */
@RestController
@RequestMapping("/course")
@Api(description = "课程")
@Slf4j
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/list/{locationId}")
	@ApiOperation(value = "查看课程列表")
	public ResultVo<PageInfo<CourseVo>> getCourseList(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size,
			@PathVariable Integer locationId) {
		log.info("查询对应地区的课程列表,地区id:{}",locationId);
		PageInfo<CourseVo> list = courseService.getCourseList(page, size, locationId);
		return ResultVoUtil.success(list);
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "首页课程显示")
	public ResultVo<PageInfo<CourseVo>> getCourseList(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("首页课程显示");
		PageInfo<CourseVo> list = courseService.getCourseList(page, size);
		return ResultVoUtil.success(list);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "查看课程详情")
	public ResultVo<CourseVo> getCourse(@PathVariable Integer id) {
		log.info("查看课程详情,课程id:{}",id);
		CourseVo course = courseService.getCourse(id);
		return ResultVoUtil.success(course);
	}

	@PostMapping
	@ApiOperation(value = "添加课程")
	public ResultVo<Course> addCourse(@Valid @RequestBody CourseDto courseDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.warn("添加课程参数错误:{}" + errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("添加课程,课程标题:{}",courseDto.getTitle());
		Course course = new Course();
		BeanUtils.copyProperties(courseDto, course);
		courseService.addCourse(course);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除课程")
	public ResultVo<Course> deleteCourse(@PathVariable Integer id) {
		log.info("删除课程,课程id:{}",id);
		courseService.deleteCourse(id);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation(value = "修改课程")
	public ResultVo<Course> updateCourse(@Valid @RequestBody CourseUpdateDto courseDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.warn("修改课程信息参数错误:{}" + errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("修改课程信息,课程id:{}",courseDto.getId());
		Course course = new Course();
		BeanUtils.copyProperties(courseDto, course);
		courseService.updateCourse(course);
		return ResultVoUtil.success();
	}

	@PutMapping("/watch/{id}")
	@ApiOperation(value = "修改课程观看次数")
	public ResultVo<Course> updateCourse(@PathVariable Integer id) {
		log.info("修改课程观看次数,课程id:{}",id);
		courseService.updateCourseTimes(id);
		return ResultVoUtil.success();
	}

	
	@GetMapping("/get/{name}")
	@ApiOperation(value = "通过课程名称或者老师名称模糊查询")
	public ResultVo<PageInfo<CourseVo>> getArticleLike(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size,
			@PathVariable String name) {
		log.info("模糊查询课程信息,关键字:{}",name);
		PageInfo<CourseVo> circle = courseService.seleteByLike(page, size, name);
		return ResultVoUtil.success(circle);
	}

}
