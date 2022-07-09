package com.fishandchips.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart {
    private List<CartItem> cartItemList;
    private Double totalMoney;
    private Integer totalGameCount;

    public Cart() {
        this.cartItemList = new ArrayList<>();
        this.totalMoney = 0.0;
        this.totalGameCount = 0;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemList != null && cartItemList.size() > 0) {
            for (CartItem cartItem : cartItemList) {
                totalMoney += cartItem.getGameEntity().getPrice();
            }
        }
        return totalMoney;
    }

    public Integer getTotalGameCount() {
        totalGameCount = 0;
        if (cartItemList != null) {
            totalGameCount = cartItemList.size();
        }
        return totalGameCount;
    }
}
