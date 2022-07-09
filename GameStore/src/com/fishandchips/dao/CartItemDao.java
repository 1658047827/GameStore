package com.fishandchips.dao;

import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.Game;
import com.fishandchips.pojo.User;

import java.util.List;

public interface CartItemDao {
    void addCartItem(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);

    //根据游戏id查询在购物车中的物品
    CartItem queryCartItemByGameIdAndUserId(Integer gameId,Integer userId);
}
