package com.fishandchips.service.impl;

import com.fishandchips.dao.OwnGameDao;
import com.fishandchips.dao.impl.OwnGameDaoImpl;
import com.fishandchips.pojo.Cart;
import com.fishandchips.pojo.CartItem;
import com.fishandchips.pojo.OwnGame;
import com.fishandchips.service.OwnGameService;

import java.util.List;

public class OwnGameServiceImpl implements OwnGameService {
    private OwnGameDao ownGameDao = new OwnGameDaoImpl();

    @Override
    public List<OwnGame> showUserOwnGame(Integer userId) {
        return ownGameDao.queryOwngameByUserId(userId);
    }

    @Override
    public OwnGame getOwnGameByUserIdAndGameId(Integer userId, Integer gameId) {
        return ownGameDao.queryOwnGameByGameIdAndUserId(gameId, userId);
    }

    @Override
    public int addOwnGame(Integer userId, Cart cart) {
        for (CartItem cartItem : cart.getCartItemList()) {
            ownGameDao.addOwnGame(new OwnGame(userId, cartItem.getGame()));
        }
        return 0;
    }

    @Override
    public int deleteOwnGame(Integer userId, List<OwnGame> ownGameList) {
        for(OwnGame ownGame:ownGameList){
            ownGameDao.deleteOwnGame(ownGame);
        }
        return 0;
    }
}
