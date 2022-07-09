package com.fishandchips.service;

import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.Order;
import com.fishandchips.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart,Integer userId);

    List<Order> showAllNormalOrders();

    List<Order> showAllRefundingOrders();

    List<Order> showAllRefundedOrders();

    List<Order> showMyNormalOrders(Integer userId);

    List<Order> showMyRefundingOrders(Integer userId);

    List<Order> showMyRefundedOrders(Integer userId);

    List<OrderItem> getOrderItemByOrderNo(String orderNo);

    int applyRefund(String orderNo);

    int cancelRefund(String orderNo);

    int permitRefund(String orderNo);

    Order getOrderByOrderNo(String orderNo);
}
