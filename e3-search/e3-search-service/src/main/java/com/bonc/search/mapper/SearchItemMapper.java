package com.bonc.search.mapper;

import java.util.List;

import com.bonc.common.pojo.SearchItem;

public interface SearchItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
