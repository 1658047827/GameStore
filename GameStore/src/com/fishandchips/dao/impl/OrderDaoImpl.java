package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.OrderDao;
import com.fishandchips.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order values(?,?,?,?,?)";
        return update(sql, order.getOrderNo(), order.getOrderDate(), order.getOrderUser(), order.getOrderMoney(),
                order.getOrderStatus());
    }

    @Override
    public List<Order> queryOrdersByStatus(Integer status) {
        String sql = "select * from t_order where orderStatus = ?;";
        return queryForList(Order.class, sql, status);
    }

    @Override
    public int changeOrderStatus(String orderNo, Integer status) {
        String sql = "update t_order set orderStatus=? where orderNo=?";
        return update(sql, status, orderNo);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select * from t_order where orderUser=?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public List<Order> queryOrdersByUserIdAndStatus(Integer userId, Integer status) {
        String sql = "select * from t_order where orderUser=? and orderStatus=?";
        return queryForList(Order.class, sql, userId, status);
    }

    @Override
    public Order queryOrderByOrderNo(String orderNo) {
        String sql="select * from t_order where orderNo=?";
        return queryForOne(Order.class,sql,orderNo);
    }
}
