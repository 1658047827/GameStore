package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.GameDao;
import com.fishandchips.pojo.Game;

import java.util.List;

public class GameDaoImpl extends BaseDao implements GameDao {
    @Override
    public List<Game> queryAllGames() {
        String sql = "select * from game;";
        return queryForList(Game.class, sql);
    }

    @Override
    public int addGame(Game game) {
        String sql = "insert into game(gameImg,gameName,price,company,saleCount,description,detailGameImg,gameType) " +
                "values(?,?,?,?,?,?,?,?)";
        return update(sql, game.getGameImg(), game.getGameName(), game.getPrice(), game.getCompany(),
                game.getSaleCount(),
                game.getDescription(), game.getDetailGameImg(), game.getGameType());
    }

    @Override
    public int deleteGameById(Integer id) {
        String sql = "delete from game where id=?";
        return update(sql, id);
    }

    @Override
    public int updateGame(Game game) {
        String sql = "update game set gameImg=?,gameName=?,price=?,company=?,saleCount=?,description=?," +
                "detailGameImg=?," +
                "gameType=? where id=?";
        return update(sql, game.getGameImg(), game.getGameName(), game.getPrice(), game.getCompany(),
                game.getSaleCount(),
                game.getDescription(), game.getDetailGameImg(), game.getGameType(), game.getId());
    }

    @Override
    public int updateGameSaleCountById(Integer gameId, Integer saleCount) {
        String sql = "update game set saleCount=? where id=?";
        return update(sql, saleCount, gameId);
    }

    @Override
    public Game queryGameById(Integer id) {
        String sql = "select * from game where id=?";
        return queryForOne(Game.class, sql, id);
    }

    @Override
    public List<Game> queryGamesByType(String gameType) {
        String sql = "select * from game where gameType like ? ";
        gameType = '%' + gameType + '%';
        return queryForList(Game.class, sql, gameType);
    }

    @Override
    public List<Game> queryGameByName(String gameName) {
        String sql = "select * from game where gameName like ?";
        gameName = '%' + gameName + '%';
        return queryForList(Game.class, sql, gameName);
    }
}
