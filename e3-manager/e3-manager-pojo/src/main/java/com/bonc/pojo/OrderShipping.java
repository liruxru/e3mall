package com.bonc.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class OrderShipping implements Serializable {
    private String orderId;

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

    private Date created;

    private Date updated;
}