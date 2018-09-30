package com.bonc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.pojo.EasyUITreeNode;
import com.bonc.common.utils.E3Result;
import com.bonc.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> listContenrCategory(@RequestParam(name="id",defaultValue="0")Long parentId) {
		return contentCategoryService.getContenrCategoryList(parentId);
	}
	
	
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContenrCategory(Long parentId,String name) {
		return contentCategoryService.addContentCategory(parentId,name);
	}
	
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateContenrCategory(Long id,String name) {
		return contentCategoryService.updateContentCategory(id,name);
	}
	
	@RequestMapping(value="/content/category/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteContenrCategory(Long id) {
		return contentCategoryService.deleteContentCategory(id);
	}
	
	
}
