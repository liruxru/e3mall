package com.bonc.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.pojo.Item;
import com.bonc.service.ItemService;

@RestController
//@RequestMapping(produces="text/json;charset=utf-8") // 这种解决方案也可以解决json乱码
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("item/{itemId}")
	@ResponseBody
	public Item getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGridResult<Item> getItemByList(Integer page,Integer rows) {
		return itemService.getItemList(page, rows);
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result ItemSave(Item item,String desc) {
		return itemService.saveItem(item,desc);
	}
	
	
	

}
