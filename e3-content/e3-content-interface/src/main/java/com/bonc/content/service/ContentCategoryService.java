package com.bonc.content.service;

import java.util.List;

import com.bonc.common.pojo.EasyUITreeNode;
import com.bonc.common.utils.E3Result;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContenrCategoryList(Long parentId);

	E3Result addContentCategory(Long parentId, String name);

	E3Result updateContentCategory(Long id, String name);

	E3Result deleteContentCategory(Long id);

}
