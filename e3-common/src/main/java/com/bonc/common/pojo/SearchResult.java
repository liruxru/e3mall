package com.bonc.common.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SearchResult implements Serializable {
	private List<SearchItem> itemList;
	private int totalPages;
	private int recourdCount;

}
