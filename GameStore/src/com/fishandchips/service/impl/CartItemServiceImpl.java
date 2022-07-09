package com.fishandchips.service.impl;

import com.fishandchips.dao.CartItemDao;
import com.fishandchips.dao.impl.CartItemDaoImpl;
import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.User;
import com.fishandchips.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao = new CartItemDaoImpl();

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemDao.deleteCartItem(cartItem);
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);
        Cart cart = new Cart(); //封装入一个Cart
        cart.setCartItemList(cartItemList);
        return cart;
    }

    @Override
    public CartItem getCartItemByGameIdAndUserId(Integer gameId, Integer userId) {
        return cartItemDao.queryCartItemByGameIdAndUserId(gameId, userId);
    }
}
