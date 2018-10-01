package com.bonc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.content.service.ContentService;
import com.bonc.pojo.Content;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGridResult<Content> getContentByCategoryId(Integer page,Integer rows,Long categoryId){
		return contentService.getContentByCategoryId(page,rows,categoryId);
	}
	
	@RequestMapping(value="content/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(Content content){
		return contentService.addContent(content);
	}
	
	/**
	 * 更新，删除
	 */

}
