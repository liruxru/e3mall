package com.bonc.common.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EasyUITreeNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8608663097715765073L;
	private long id;
	private String text;
	private String state;  // 是否为可展开节点
	
}
