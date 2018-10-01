package com.bonc.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonc.content.service.ContentService;
import com.bonc.pojo.Content;

@Controller
public class IndexController {
	@Autowired
	private ContentService ContentService;
	
	@RequestMapping("index")
	public String index(Model model) {
		List<Content> listContentByCid = ContentService.getListContentByCid(89L);
		model.addAttribute("ad1List", listContentByCid);
		return "index";
	}
}
