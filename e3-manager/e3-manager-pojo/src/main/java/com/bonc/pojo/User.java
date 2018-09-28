package com.bonc.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

}