package com.fishandchips.web;

import com.fishandchips.pojo.Game;
import com.fishandchips.service.GameService;
import com.fishandchips.service.impl.GameServiceImpl;
import com.fishandchips.utils.FileUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends ViewBaseServlet {
    private GameService gameService = new GameServiceImpl();

    protected void showGameDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Integer gameId = Integer.valueOf(req.getParameter("gameId"));
        Game gameDetail = gameService.queryGameById(gameId);

        req.getSession().setAttribute("gameDetail", gameDetail);

        //设置不弹出添加购物车结果窗口
        req.setAttribute("addToCartResult", "0");
        req.setAttribute("buySingleResult", "0");

        super.processTemplate("WEB-INF/pages/game_detail", req, resp);
    }

    protected void gameAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession session = req.getSession();

        //获取所有的游戏信息
        List<Game> games = gameService.getAll();

        //保存到session作用域
        session.setAttribute("gameList", games);

        super.processTemplate("WEB-INF/pages/game_admin_page/game_admin_gameinf", req, resp);
    }


    protected void deleteGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer gameId = Integer.valueOf(req.getParameter("gameId"));
        gameService.deleteGameById(gameId);

        resp.sendRedirect("/GameStore/game?gameAdmin=1");
    }

    protected void showAddGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("WEB-INF/pages/game_admin_page/game_admin_addgame", req, resp);
    }

    protected void addGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = FileUploadUtil.process(req);
        System.out.println(game);
        if (game == null) {
            System.out.println("upload error");
        } else {
            gameService.addGame(game);

            resp.sendRedirect("/GameStore/game?gameAdmin=1");
        }
    }

    protected void searchGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("gameName");
        if (name != null) {
            name = new String(name.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        }

        List<Game> gameList = gameService.searchGameByName(name);

        req.getSession().setAttribute("gameList", gameList);

        super.processTemplate("WEB-INF/pages/search_result", req, resp);
    }

    protected void editGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int gameId = Integer.parseInt(req.getParameter("gameId"));
        String gameName = new String( req.getParameter("gameName").getBytes("ISO8859-1"), StandardCharsets.UTF_8);

        String gameCompany = new String( req.getParameter("gameCompany").getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        double gamePrice = Double.parseDouble(req.getParameter("gamePrice"));
        String gameType = new String( req.getParameter("gameType").getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        String gameDescription = new String( req.getParameter("gameDescription").getBytes("ISO8859-1"),
                StandardCharsets.UTF_8);

        Game gameEditing = (Game) req.getSession().getAttribute("gameEditing");

        gameService.updateGame(new Game(gameId, gameEditing.getGameImg(), gameName, gamePrice, gameCompany,
                gameEditing.getSaleCount(),
                gameDescription, gameType, gameEditing.getDetailGameImg()));

        resp.sendRedirect("/GameStore/game?gameAdmin=1");
    }

    protected void toEditGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer gameId = Integer.valueOf(req.getParameter("gameId"));
        Game gameEditing = gameService.queryGameById(gameId);
        req.getSession().setAttribute("gameEditing", gameEditing);

        super.processTemplate("WEB-INF/pages/game_admin_page/game_admin_editgame", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("op") != null && req.getParameter("op").equals("editGame")) {
            editGame(req, resp);
        }

        if (req.getParameter("searchGame") != null && req.getParameter("searchGame").equals("1")) {
            searchGame(req, resp);
        }

        if (req.getParameter("op") != null && req.getParameter("op").equals("addGame")) {
            addGame(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("showDetail") != null && req.getParameter("showDetail").equals("1")) {
            showGameDetail(req, resp);
        }

        if (req.getParameter("gameAdmin") != null && req.getParameter("gameAdmin").equals("1")) {
            gameAdmin(req, resp);
        }

        if (req.getParameter("deleteGame") != null && req.getParameter("deleteGame").equals("1")) {
            deleteGame(req, resp);
        }

        if (req.getParameter("showAddGame") != null && req.getParameter("showAddGame").equals("1")) {
            showAddGame(req, resp);
        }

        if (req.getParameter("toEditGame") != null && req.getParameter("toEditGame").equals("1")) {
            toEditGame(req, resp);
        }
    }
}
