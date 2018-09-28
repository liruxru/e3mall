package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class ItemDesc {
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

   
}