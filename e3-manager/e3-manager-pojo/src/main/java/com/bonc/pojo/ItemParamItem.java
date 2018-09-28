package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class ItemParamItem {
    private Long id;

    private Long itemId;

    private Date created;

    private Date updated;

    private String paramData;

}