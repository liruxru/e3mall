package com.bonc.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.bonc.common.jedis.JedisClient;
import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.common.utils.JacksonUtils;
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
	@Autowired
	private  JedisClient jedisClient;
	@Value("")
	private String CONTENT_LIST;

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
		// 清除缓存,缓存同步
		try {
			jedisClient.hdel(CONTENT_LIST,content.getCategoryId()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return E3Result.ok();
	}
	
	/**

	 * 缓存处理
	 */
	@Override
	public List<Content> getListContentByCid(Long categoryId) {
		// 查詢緩存
		try {
			// 命中缓存直接返回
			String json = jedisClient.hget(CONTENT_LIST,categoryId+"");
			if(StringUtils.isNotEmpty(json)) {
				return JacksonUtils.jsonToList(json, Content.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentExample contentExample = new ContentExample();	
		Criteria createCriteria = contentExample.createCriteria();
		createCriteria.andCategoryIdEqualTo(categoryId);
		List<Content> list = contentMapper.selectByExampleWithBLOBs(contentExample);
		
		// 添加结果到缓存
		try {
			jedisClient.hset(CONTENT_LIST, categoryId+"", JacksonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
