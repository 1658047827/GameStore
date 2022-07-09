package com.fishandchips.test;

import com.fishandchips.pojo.Game;
import com.fishandchips.service.GameService;
import com.fishandchips.service.impl.GameServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameServiceTest {
    private GameService gameService = new GameServiceImpl();

    @Test
    public void getAll() {
        List<Game> all = gameService.getAll();
        for(Game game : all){
            System.out.println(game);
        }
    }
}