package com.bonc.service;

import java.util.List;

import com.bonc.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	List<EasyUITreeNode> getItemCatList(Long parentId);

}
