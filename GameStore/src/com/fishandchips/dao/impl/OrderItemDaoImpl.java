package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.OrderItemDao;
import com.fishandchips.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item(game,orderBean) values(?,?)";
        return update(sql, orderItem.getGame(), orderItem.getOrderBean());
    }

    @Override
    public OrderItem queryOrderItemById(Integer orderItemId) {
        String sql = "select * from order_item where id=?";
        return queryForOne(OrderItem.class, sql, orderItemId);
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderNo(String orderNo) {
        String sql="select * from order_item where orderBean=?";
        return queryForList(OrderItem.class,sql,orderNo);
    }
}
