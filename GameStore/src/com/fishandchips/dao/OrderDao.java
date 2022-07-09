package com.fishandchips.dao;

import com.fishandchips.pojo.Order;
import com.fishandchips.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
    int saveOrder(Order order);

    //根据状态查询所有的订单（不管是谁）
    List<Order> queryOrdersByStatus(Integer status);

    //改变订单状况
    int changeOrderStatus(String orderNo, Integer status);

    List<Order> queryOrdersByUserId(Integer userId);

    List<Order> queryOrdersByUserIdAndStatus(Integer userId, Integer status);

    Order queryOrderByOrderNo(String orderNo);
}
