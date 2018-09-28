package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class Content {
    private Long id;

    private Long categoryId;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private Date created;

    private Date updated;

    private String content;

   
}