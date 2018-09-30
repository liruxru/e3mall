package com.bonc.common.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIDataGridResult<T> implements Serializable {
	private long total;
	private List<T> rows;
}
