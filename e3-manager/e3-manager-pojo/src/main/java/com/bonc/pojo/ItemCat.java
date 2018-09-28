package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class ItemCat {
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;

   
}