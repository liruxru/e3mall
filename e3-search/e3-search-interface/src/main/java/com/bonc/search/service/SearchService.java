package com.bonc.search.service;

import com.bonc.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String keyWord, int page, int rows) throws Exception;

}
