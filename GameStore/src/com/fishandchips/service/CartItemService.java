package com.fishandchips.service;

import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.User;

public interface CartItemService {
    public void addCartItem(CartItem cartItem);

    public void deleteCartItem(CartItem cartItem);

    Cart getCart(User user);

    CartItem getCartItemByGameIdAndUserId(Integer gameId,Integer userId);
}
