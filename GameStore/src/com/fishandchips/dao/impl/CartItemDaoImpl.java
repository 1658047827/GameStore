package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.CartItemDao;
import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.User;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.util.List;

public class CartItemDaoImpl extends BaseDao implements CartItemDao {
    @Override
    public void addCartItem(CartItem cartItem) {
        String sql = "insert into cart_item(game,userBean) values(?,?)";
        update(sql, cartItem.getGame(), cartItem.getUserBean());
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        String sql = "delete from cart_item where id=?";
        update(sql, cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from cart_item where userBean=?;";
        return queryForList(CartItem.class, sql, user.getId());
    }

    @Override
    public CartItem queryCartItemByGameIdAndUserId(Integer gameId, Integer userId) {
        String sql = "select * from cart_item where game=? and userBean=?";
        return queryForOne(CartItem.class, sql, gameId, userId);
    }
}
