package com.bonc.service.impl;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bonc.service.ItemService;

public class main {
	public static void main(String[] args) throws IOException {
		ApplicationContext app  = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
		
		System.out.println("服务已经启动");
		System.in.read();
		System.out.println("服务终止");
	}
}
