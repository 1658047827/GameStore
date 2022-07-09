package com.fishandchips.web;

import com.fishandchips.dao.OwnGameDao;
import com.fishandchips.pojo.Game;
import com.fishandchips.pojo.OwnGame;
import com.fishandchips.service.GameService;
import com.fishandchips.service.OwnGameService;
import com.fishandchips.service.impl.GameServiceImpl;
import com.fishandchips.service.impl.OwnGameServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    private GameService gameService = new GameServiceImpl();
    private OwnGameService ownGameService = new OwnGameServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        //获取所有的游戏信息
        List<Game> games = gameService.getAll();

        //保存到session作用域
        session.setAttribute("gameList", games);

        if (session.getAttribute("isLoggedin") == null) {
            session.setAttribute("isLoggedin", 0);
        }

        super.processTemplate("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("selectGame") != null && req.getParameter("selectGame").equals("1")) {
            gameTypeList(req, resp);
        }
    }

    protected void gameTypeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.setCharacterEncoding("utf-8");
        String gameType = req.getParameter("selectGameType");

        List<Game> games = null;

        if (gameType != null && gameType.equals("all")) {
            //获取所有的游戏信息
            games = gameService.getAll();
        } else if (gameType != null) {
            //根据类型获取游戏
            games = gameService.getTypeGame(gameType);
        }

        //保存到session作用域
        session.setAttribute("gameList", games);

        super.processTemplate("index", req, resp);
    }
}
