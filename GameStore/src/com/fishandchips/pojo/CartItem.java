package com.fishandchips.pojo;

import com.fishandchips.dao.GameDao;
import com.fishandchips.dao.UserDao;
import com.fishandchips.dao.impl.GameDaoImpl;
import com.fishandchips.dao.impl.UserDaoImpl;

public class CartItem {
    private Integer id;
    private Integer game;
    private Integer userBean;

    private Game gameEntity;
    private User userEntity;

    public CartItem() {
        gameEntity = null;
        userEntity = null;
    }

    public CartItem(Integer id, Integer game, Integer userBean) {
        this.id = id;
        this.game = game;
        this.userBean = userBean;

        if (game != null) {
            GameDao gameDao = new GameDaoImpl();
            gameEntity = gameDao.queryGameById(game);
        } else {
            gameEntity = null;
        }

        if (userBean != null) {
            UserDao userDao = new UserDaoImpl();
            userEntity = userDao.queryUserById(userBean);
        } else {
            userEntity = null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        if (game != null) {
            GameDao gameDao = new GameDaoImpl();
            gameEntity = gameDao.queryGameById(game);
            this.game = game;
        } else {
            gameEntity = null;
        }
    }

    public Integer getUserBean() {
        return userBean;
    }

    public void setUserBean(Integer userBean) {
        if (userBean != null) {
            UserDao userDao = new UserDaoImpl();
            userEntity = userDao.queryUserById(userBean);
            this.userBean = userBean;
        } else {
            userEntity = null;
        }
    }

    /* 这两个只读 */
    public Game getGameEntity() {
        return gameEntity;
    }

    public User getUserEntity() {
        return userEntity;
    }
}
