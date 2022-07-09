package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.OwnGameDao;
import com.fishandchips.pojo.OwnGame;

import java.util.List;

public class OwnGameDaoImpl extends BaseDao implements OwnGameDao {
    @Override
    public List<OwnGame> queryOwngameByUserId(Integer userId) {
        String sql = "select * from own_game where userId=?";
        return queryForList(OwnGame.class, sql, userId);
    }

    @Override
    public OwnGame queryOwnGameByGameIdAndUserId(Integer gameId, Integer userId) {
        String sql = "select * from own_game where userId=? and gameId=?";
        return queryForOne(OwnGame.class, sql, userId, gameId);
    }

    @Override
    public int addOwnGame(OwnGame ownGame) {
        String sql = "insert into own_game values(?,?)";
        return update(sql, ownGame.getUserId(), ownGame.getGameId());
    }

    @Override
    public int deleteOwnGame(OwnGame ownGame) {
        String sql = "delete from own_game where userId=? and gameId=?";
        return update(sql, ownGame.getUserId(), ownGame.getGameId());
    }
}
