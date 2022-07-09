package com.fishandchips.service.impl;

import com.fishandchips.dao.CartItemDao;
import com.fishandchips.dao.GameDao;
import com.fishandchips.dao.OrderDao;
import com.fishandchips.dao.OrderItemDao;
import com.fishandchips.dao.impl.CartItemDaoImpl;
import com.fishandchips.dao.impl.GameDaoImpl;
import com.fishandchips.dao.impl.OrderDaoImpl;
import com.fishandchips.dao.impl.OrderItemDaoImpl;
import com.fishandchips.pojo.*;
import com.fishandchips.service.OrderService;

import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private CartItemDao cartItemDao = new CartItemDaoImpl();
    private GameDao gameDao = new GameDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderNo = System.currentTimeMillis() + "" + userId;

        Order order = new Order(orderNo, new Date(), cart.getTotalMoney(), 0, userId);
        orderDao.saveOrder(order);

        Map<Integer, Integer> gameSaleMap = new HashMap<>(); //gameId,saleCount

        //遍历购物车中每一个商品项，统计对应的销量，转换为订单项保存到数据库，同时清空购物车
        for (CartItem cartItem : cart.getCartItemList()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getGame(), orderNo);
            //统计销量
            if(gameSaleMap.get(cartItem.getGame())==null){
                gameSaleMap.put(cartItem.getGame(),cartItem.getGameEntity().getSaleCount()+1);
            }else{
                Integer saleBefore=gameSaleMap.get(cartItem.getGame());
                gameSaleMap.put(cartItem.getGame(),saleBefore+1);
            }
            orderItemDao.saveOrderItem(orderItem);
            cartItemDao.deleteCartItem(cartItem);
        }

        //更新销量
        for (Map.Entry<Integer, Integer> entry : gameSaleMap.entrySet()){
            gameDao.updateGameSaleCountById(entry.getKey(), entry.getValue());
        }

        return orderNo;
    }

    @Override
    public List<Order> showAllNormalOrders() {
        return orderDao.queryOrdersByStatus(0);
    }

    @Override
    public List<Order> showAllRefundingOrders() {
        return orderDao.queryOrdersByStatus(1);
    }

    @Override
    public List<Order> showAllRefundedOrders() {
        return orderDao.queryOrdersByStatus(2);
    }

    @Override
    public List<Order> showMyNormalOrders(Integer userId) {
        return orderDao.queryOrdersByUserIdAndStatus(userId, 0);
    }

    @Override
    public int applyRefund(String orderNo) {
        return orderDao.changeOrderStatus(orderNo, 1);
    }

    @Override
    public int permitRefund(String orderNo) {
        return orderDao.changeOrderStatus(orderNo, 2);
    }

    @Override
    public int cancelRefund(String orderNo) {
        return orderDao.changeOrderStatus(orderNo, 0);
    }

    @Override
    public List<Order> showMyRefundingOrders(Integer userId) {
        return orderDao.queryOrdersByUserIdAndStatus(userId, 1);
    }

    @Override
    public List<Order> showMyRefundedOrders(Integer userId) {
        return orderDao.queryOrdersByUserIdAndStatus(userId, 2);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderNo(String orderNo) {
        return orderItemDao.queryOrderItemByOrderNo(orderNo);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        return orderDao.queryOrderByOrderNo(orderNo);
    }
}
