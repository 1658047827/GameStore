package com.fishandchips.pojo;

public class OwnGame {
    private Integer userId;
    private Integer gameId;

    public OwnGame() {
    }

    public OwnGame(Integer userId, Integer gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
