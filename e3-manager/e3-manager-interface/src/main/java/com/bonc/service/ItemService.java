package com.bonc.service;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.pojo.Item;

public interface ItemService {
	
	Item getItemById(Long itemId);
	EasyUIDataGridResult<Item> getItemList(int page,int rows);
	E3Result saveItem(Item item, String desc);

}
