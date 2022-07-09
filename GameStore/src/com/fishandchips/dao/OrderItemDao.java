package com.fishandchips.dao;

import com.fishandchips.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);

    OrderItem queryOrderItemById(Integer orderItemId);

    List<OrderItem> queryOrderItemByOrderNo(String orderNo);
}
