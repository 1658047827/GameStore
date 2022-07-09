package com.fishandchips.pojo;

import com.fishandchips.dao.GameDao;
import com.fishandchips.dao.impl.GameDaoImpl;

public class OrderItem {
    private Integer id;
    private Integer game;
    private String orderBean;

    private Game gameEntity;

    public OrderItem() {
        gameEntity = null;
    }

    public OrderItem(Integer id, Integer game, String orderBean) {
        this.id = id;
        this.game = game;
        this.orderBean = orderBean;
        GameDao gameDao = new GameDaoImpl();
        gameEntity = gameDao.queryGameById(game);
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
        }
    }

    public String getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(String orderBean) {
        this.orderBean = orderBean;
    }

    public Game getGameEntity() {
        return gameEntity;
    }
}
