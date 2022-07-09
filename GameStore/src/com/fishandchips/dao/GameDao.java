package com.fishandchips.dao;

import com.fishandchips.pojo.Game;

import java.util.List;

public interface GameDao {
    /* 获取所有的游戏信息 */
    List<Game> queryAllGames();

    int addGame(Game game);

    int deleteGameById(Integer id);

    int updateGame(Game game);

    int updateGameSaleCountById(Integer gameId,Integer saleCount);

    Game queryGameById(Integer id);

    List<Game> queryGamesByType(String gameType);

    List<Game> queryGameByName(String gameName);
}
