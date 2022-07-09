package com.fishandchips.pojo;

import java.util.Date;

public class Order {
    private String orderNo;
    private Object orderDate;
    private Double orderMoney;
    //0表示已支付，1表示申请退款，2表示已退款
    private Integer orderStatus = 0;
    private Integer orderUser;

    public Order() {
    }

    public Order(String orderNo, Object orderDate, Double orderMoney, Integer orderStatus,
                 Integer orderUser) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.orderUser = orderUser;
    }

    public Object getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Object orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Integer orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
