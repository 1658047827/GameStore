package com.fishandchips.service.impl;

import com.fishandchips.dao.GameDao;
import com.fishandchips.dao.impl.GameDaoImpl;
import com.fishandchips.pojo.Game;
import com.fishandchips.service.GameService;

import java.util.List;

public class GameServiceImpl implements GameService {
    private GameDao gameDao = new GameDaoImpl();

    @Override
    public List<Game> getAll() {
        return gameDao.queryAllGames();
    }

    @Override
    public void addGame(Game game) {
        gameDao.addGame(game);
    }

    @Override
    public void deleteGameById(Integer id) {
        gameDao.deleteGameById(id);
    }

    @Override
    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    @Override
    public Game queryGameById(Integer id) {
        return gameDao.queryGameById(id);
    }

    @Override
    public List<Game> getTypeGame(String gameType) {
        return gameDao.queryGamesByType(gameType);
    }

    @Override
    public List<Game> searchGameByName(String gameName) {
        return gameDao.queryGameByName(gameName);
    }
}
