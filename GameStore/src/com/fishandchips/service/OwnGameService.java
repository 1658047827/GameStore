package com.fishandchips.service;

import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.Order;
import com.fishandchips.pojo.OwnGame;

import java.util.List;

public interface OwnGameService {
    List<OwnGame> showUserOwnGame(Integer userId);

    OwnGame getOwnGameByUserIdAndGameId(Integer userId,Integer gameId);

    int addOwnGame(Integer userId, Cart cart);

    int deleteOwnGame(Integer userId, List<OwnGame> ownGameList);
}
