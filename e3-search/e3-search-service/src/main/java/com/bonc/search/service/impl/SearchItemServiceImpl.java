package com.bonc.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.common.pojo.SearchItem;
import com.bonc.common.utils.E3Result;
import com.bonc.search.mapper.SearchItemMapper;
import com.bonc.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public E3Result importItmes() {
		try {
			//查询商品列表
			List<SearchItem> itemList = searchItemMapper.getItemList();
			//导入索引库
			for (SearchItem searchItem : itemList) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回成功
			return E3Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "商品导入失败");
		}
	}

	@Override
	public E3Result addItme(long itemId) {
		try {
			//查询商品列表
			SearchItem searchItem = searchItemMapper.getItemById(itemId);
			//导入索引库
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//向文档中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			//写入索引库
			solrServer.add(document);
			//提交
			solrServer.commit();
			//返回成功
			return E3Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "商品导入失败");
		}
	}



}

