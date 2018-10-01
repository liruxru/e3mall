package com.bonc.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.content.service.ContentService;
import com.bonc.mapper.ContentMapper;
import com.bonc.pojo.Content;
import com.bonc.pojo.ContentExample;
import com.bonc.pojo.ContentExample.Criteria;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private  ContentMapper contentMapper;

	@Override
	public EasyUIDataGridResult<Content> getContentByCategoryId(Integer page, Integer rows, Long categoryId) {
		PageHelper.startPage(page, rows);
		ContentExample contentExample = new ContentExample();	
		Criteria createCriteria = contentExample.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		List<Content> list = contentMapper.selectByExample(contentExample);
		long total = ((Page<Content>)list).getTotal();
		
		return new EasyUIDataGridResult<Content>(total,list);
	}

	@Override
	public E3Result addContent(Content content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return E3Result.ok();
	}

	@Override
	public List<Content> getListContentByCid(Long categoryId) {
		ContentExample contentExample = new ContentExample();	
		Criteria createCriteria = contentExample.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		List<Content> list = contentMapper.selectByExampleWithBLOBs(contentExample);
		return list;
	}

}
