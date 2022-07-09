package com.fishandchips.pojo;

public class Game {
    private Integer id;
    private String gameImg;
    private String gameName;
    private Double price;
    private String company;
    private Integer saleCount;
    private String description;
    private String gameType;
    private String detailGameImg;

    public Game() {
    }

    public Game(Integer id, String gameImg, String gameName, Double price, String company, Integer saleCount,
                String description, String gameType, String detailGameImg) {
        this.id = id;
        if (gameImg != null && !gameImg.equals("")) {
            this.gameImg = gameImg;
        }
        this.gameName = gameName;
        this.price = price;
        this.company = company;
        this.saleCount = saleCount;
        this.description = description;
        this.gameType = gameType;
        this.detailGameImg = detailGameImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameImg() {
        return gameImg;
    }

    public void setGameImg(String gameImg) {
        if (gameImg != null && !gameImg.equals("")) {
            this.gameImg = gameImg;
        }
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }


    public String getDetailGameImg() {
        return detailGameImg;
    }

    public void setDetailGameImg(String detailGameImg) {
        this.detailGameImg = detailGameImg;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameImg='" + gameImg + '\'' +
                ", gameName='" + gameName + '\'' +
                ", price=" + price +
                ", company='" + company + '\'' +
                ", saleCount=" + saleCount +
                '}';
    }
}
