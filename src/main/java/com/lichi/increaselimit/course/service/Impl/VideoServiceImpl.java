package com.lichi.increaselimit.course.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.course.dao.VideoMapper;
import com.lichi.increaselimit.course.entity.Video;
import com.lichi.increaselimit.course.service.VideoService;

/**
 * VideoService
 * @author majie
 *
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public Video getVideo(Integer id) {
		return videoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addVideo(Video video) {
		videoMapper.insertSelective(video);
	}

	@Override
	public void deleteVideo(Integer id) {
		videoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateVideo(Video video) {
		videoMapper.updateByPrimaryKey(video);
	}

	@Override
	public PageInfo<Video> getVideoList(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Video> list = videoMapper.selectAll();
		PageInfo<Video> info = new PageInfo<>(list);
		return info;
	}



}
