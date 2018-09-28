package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class Item {
    private Long id;

    private String title;

    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private String image;

    private Long cid;

    private Byte status;

    private Date created;

    private Date updated;

  
}