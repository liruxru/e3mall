package com.bonc.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.common.pojo.EasyUITreeNode;
import com.bonc.common.utils.E3Result;
import com.bonc.content.service.ContentCategoryService;
import com.bonc.mapper.ContentCategoryMapper;
import com.bonc.pojo.ContentCategory;
import com.bonc.pojo.ContentCategoryExample;
import com.bonc.pojo.ContentCategoryExample.Criteria;
@Service  
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private ContentCategoryMapper contentCategorySMapper;

	@Override
	public List<EasyUITreeNode> getContenrCategoryList(Long parentId) {
		// TODO Auto-generated method stub
		ContentCategoryExample contentCategoryExample = new ContentCategoryExample();
		Criteria contentCategoryCriteria = contentCategoryExample.createCriteria();
		contentCategoryCriteria.andParentIdEqualTo(parentId)
		.andStatusEqualTo(1);// 1正常 2删除
		
		List<ContentCategory> contentCategoryList = contentCategorySMapper.selectByExample(contentCategoryExample);
		
		List<EasyUITreeNode> eNodeList = new ArrayList<>();
		for (ContentCategory contentCategory : contentCategoryList) {
			EasyUITreeNode eNode = new EasyUITreeNode();
			eNode.setId(contentCategory.getId());
			eNode.setText(contentCategory.getName());
			eNode.setState(contentCategory.getIsParent()?"closed":"open");
			eNodeList.add(eNode);
		}
		
		return eNodeList;
	}

	@Override
	public E3Result addContentCategory(Long parentId, String name) {
		
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false); // 新添加的节点一定是叶子节点
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setName(name);
		
		contentCategory.setStatus(1);// 1正常2删除
		contentCategory.setSortOrder(1); // 默认排序为1
		contentCategorySMapper.insert(contentCategory);
		
		// 查询父节点更新父节点
		ContentCategory contentCategoryFo = contentCategorySMapper.selectByPrimaryKey(parentId);
		if(!contentCategoryFo.getIsParent()) {
			contentCategoryFo.setIsParent(true);
			contentCategorySMapper.updateByPrimaryKey(contentCategoryFo);
		};
		
		
		// 返回结果
		Map<String,Object> data = new HashMap<>();
		data.put("id", contentCategory.getId());
		E3Result e3Result =E3Result.ok(data);
		
		return e3Result;
	}

	@Override
	public E3Result updateContentCategory(Long id, String name) {
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		contentCategorySMapper.updateByPrimaryKeySelective(contentCategory);
		
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContentCategory(Long id) {
		// 判断有没有子节点
		// 查询父节点更新父节点
		ContentCategory contentCategoryFo = contentCategorySMapper.selectByPrimaryKey(id);
		if(contentCategoryFo.getIsParent()) {
			return new E3Result(400, "存在子节点无法删除", null);
		}
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setId(id);
		contentCategory.setStatus(2); // 1正常2删除
		contentCategory.setUpdated(new Date());
		contentCategorySMapper.updateByPrimaryKeySelective(contentCategory);
		return E3Result.ok();
	}

}
