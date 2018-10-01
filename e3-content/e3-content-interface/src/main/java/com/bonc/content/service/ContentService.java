package com.bonc.content.service;

import java.util.List;

import com.bonc.common.pojo.EasyUIDataGridResult;
import com.bonc.common.utils.E3Result;
import com.bonc.pojo.Content;

public interface ContentService {
	/**
	 * 通过分类id查询内容
	 * @param page
	 * @param rows
	 * @param categoryId
	 * @return
	 */
	EasyUIDataGridResult<Content> getContentByCategoryId(Integer page, Integer rows, Long categoryId);

	E3Result addContent(Content content);
	List<Content> getListContentByCid(Long categoryId);

}
