package com.bonc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.mapper.ItemMapper;
import com.bonc.pojo.Item;
import com.bonc.pojo.ItemExample;
import com.bonc.pojo.ItemExample.Criteria;
import com.bonc.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public Item getItemById(Long itemId) {
		// 设置查询条件
		ItemExample itemExample = new ItemExample();	
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<Item> list = itemMapper.selectByExample(itemExample);
		if(null!=list&&list.size()!=0) {
			Item item = list.get(0);
			return item;
		}
		return null;
	
		
//		return itemMapper.selectByPrimaryKey(itemId);
	}

}
