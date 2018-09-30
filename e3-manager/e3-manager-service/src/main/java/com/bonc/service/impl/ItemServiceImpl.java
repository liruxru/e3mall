package com.bonc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.common.utils.IDUtils;
import com.bonc.mapper.ItemDescMapper;
import com.bonc.mapper.ItemMapper;
import com.bonc.pojo.Item;
import com.bonc.pojo.ItemDesc;
import com.bonc.pojo.ItemExample;
import com.bonc.pojo.ItemExample.Criteria;
import com.bonc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

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
	/**
	 * 分页查询  具体文档查看github 分页插件
	 * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
	 */
	@Override
	public EasyUIDataGridResult<Item> getItemList(int page, int rows) {

		return getItemList3(page, rows);
	}
	/**
	 * 1. use static method startPage
	 * @param page
	 * @param rows
	 * @return
	 */
	private EasyUIDataGridResult<Item> getItemList1(int page, int rows) {
		
		PageHelper.startPage(page, rows);
		ItemExample itemExample = new ItemExample();	
		List<Item> list = itemMapper.selectByExample(itemExample);
		long total = ((Page<Item>)list).getTotal();

		
		return new EasyUIDataGridResult<Item>(total,list);
	}
	
	

	/**
	 * 3. use static method offsetPage
	 * @param page
	 * @param rows
	 * @return
	 */
	private EasyUIDataGridResult<Item> getItemList3(int page, int rows) {
		int offset = (page-1)*rows;
		int limit = rows;
		PageHelper.offsetPage(offset, limit);
		
		ItemExample itemExample = new ItemExample();	
		List<Item> list = itemMapper.selectByExample(itemExample);
		
		long count = PageHelper.count(()->itemMapper.selectByExample(itemExample));
		return new EasyUIDataGridResult<Item>(count,list);
	}
	@Override
	public E3Result saveItem(Item item,String desc) {
		// 
		item.setId(IDUtils.genItemId());
		// 1 正常 2下架 3 删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemMapper.insert(item);
		itemDescMapper.insert(itemDesc);
		
		return E3Result.ok();
	}



	
}
