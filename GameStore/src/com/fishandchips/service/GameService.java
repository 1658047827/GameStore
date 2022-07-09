package com.fishandchips.service;

import com.fishandchips.pojo.Game;

import java.util.List;

public interface GameService {
    List<Game> getAll();
    void addGame(Game game);
    void deleteGameById(Integer id);
    void updateGame(Game game);
    Game queryGameById(Integer id);
    List<Game> getTypeGame(String gameType);
    List<Game> searchGameByName(String gameName);
}
