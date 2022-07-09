package com.fishandchips.test;

import com.fishandchips.dao.CartItemDao;
import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.User;
import com.fishandchips.service.CartItemService;
import com.fishandchips.service.OrderService;
import com.fishandchips.service.impl.CartItemServiceImpl;
import com.fishandchips.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {
    private CartItemService cartItemService = new CartItemServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        cartItemService.addCartItem(new CartItem(null,12,17));
        cartItemService.addCartItem(new CartItem(null,12,17));
        cartItemService.addCartItem(new CartItem(null,12,17));

        Cart cart = cartItemService.getCart(new User(17, null, null, null, null));

        System.out.println(orderService.createOrder(cart,17));
    }
}