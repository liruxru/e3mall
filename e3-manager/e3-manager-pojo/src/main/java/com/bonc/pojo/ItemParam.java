package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class ItemParam {
    private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;

  
}