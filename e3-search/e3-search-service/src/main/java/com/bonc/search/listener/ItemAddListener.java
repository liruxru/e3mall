package com.bonc.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.bonc.search.service.SearchItemService;

public class ItemAddListener  implements MessageListener {
	@Autowired
	private SearchItemService searchItemService;

	
	@Override
	public void onMessage(Message message) {
		
		try {
			TextMessage textMessage = (TextMessage) message;
			//取消息内容
			String text = textMessage.getText();
			System.out.println(text);
			// 将对应的商品信息同步到索引库
			long itemId = Long.parseLong(text);
			System.out.println("\n\n\n\n\n\n\n\n\n哈哈哈哈");
			searchItemService.addItme(itemId);
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
