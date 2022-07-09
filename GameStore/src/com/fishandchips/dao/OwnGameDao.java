package com.fishandchips.dao;

import com.fishandchips.pojo.OwnGame;

import java.util.List;

public interface OwnGameDao {
    List<OwnGame> queryOwngameByUserId(Integer userId);

    OwnGame queryOwnGameByGameIdAndUserId(Integer gameId,Integer userId);

    int addOwnGame(OwnGame ownGame);

    int deleteOwnGame(OwnGame ownGame);
}
