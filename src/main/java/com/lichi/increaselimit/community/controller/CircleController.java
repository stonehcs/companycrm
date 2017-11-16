package com.lichi.increaselimit.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.community.service.ArticleService;
import com.lichi.increaselimit.community.service.CircleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author by majie on 2017/11/15.
 */
@RestController
@Api(description = "圈子")
@RequestMapping("/circle")
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ApiOperation(value = "新建圈子")
    public ResultVo<Circle> postArticle(@Valid @RequestBody Circle circle, BindingResult result){
        if(result.hasErrors()){
            String errors = result.getFieldError().getDefaultMessage();
            return ResultVoUtil.error(1,errors);
        }
        circleService.add(circle);
        return ResultVoUtil.success(circle);
    }

    @PutMapping
    @ApiOperation(value = "更新圈子信息")
    public ResultVo<Circle> update(@Valid Circle circle, BindingResult result){
        if(result.hasErrors()){
            String errors = result.getFieldError().getDefaultMessage();
            return ResultVoUtil.error(1,errors);
        }
        circleService.add(circle);
        return ResultVoUtil.success();

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除圈子")
    public ResultVo<Circle> deleteArticle(@PathVariable  Integer id){
        circleService.delete(id);
        return ResultVoUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "分页查询列表")
    public ResultVo<Page<Circle>> getArticle(@ApiParam(value = "页码",required = false) @RequestParam(defaultValue = "0",required = false) Integer page,
                                              @ApiParam(value = "条数",required = false) @RequestParam(defaultValue = "20",required = false) Integer size){
        Page<Circle> articles = circleService.getByPage(page,size);
        return ResultVoUtil.success(articles);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询圈子信息")
    public ResultVo<Circle> getArticle(@PathVariable  Integer id){
        Circle circle = circleService.get(id);
        Integer count = articleService.getCountByCircleId(id);
        circle.setCount(count);
        return ResultVoUtil.success(circle);
    }


}
