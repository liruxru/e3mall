package com.bonc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.common.pojo.EasyUITreeNode;
import com.bonc.mapper.ItemCatMapper;
import com.bonc.pojo.ItemCat;
import com.bonc.pojo.ItemCatExample;
import com.bonc.pojo.ItemCatExample.Criteria;
import com.bonc.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		// 根据parentId查询子节点列表
		ItemCatExample itemCatExample = new ItemCatExample();
		Criteria itemCatCriteria = itemCatExample.createCriteria();
		// 设置查询条件
		itemCatCriteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<ItemCat> selectByExample = itemCatMapper.selectByExample(itemCatExample);
		
		// 创建返回结果list
		List<EasyUITreeNode> tree = new ArrayList<>();
		for (ItemCat itemCat : selectByExample) {
			EasyUITreeNode eNode = new EasyUITreeNode();
			eNode.setId(itemCat.getId());
			eNode.setState(itemCat.getIsParent()?"closed":"open");
			eNode.setText(itemCat.getName());
			tree.add(eNode);
		}
		// 返回结果
		return tree;
	}

}
