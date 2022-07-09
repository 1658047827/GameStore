package com.fishandchips.web;

import com.fishandchips.pojo.Game;
import com.fishandchips.pojo.OwnGame;
import com.fishandchips.pojo.User;
import com.fishandchips.service.GameService;
import com.fishandchips.service.OwnGameService;
import com.fishandchips.service.UserService;
import com.fishandchips.service.impl.GameServiceImpl;
import com.fishandchips.service.impl.OwnGameServiceImpl;
import com.fishandchips.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends ViewBaseServlet {
    UserService userService = new UserServiceImpl();
    OwnGameService ownGameService = new OwnGameServiceImpl();
    GameService gameService = new GameServiceImpl();

    protected void showMyAccountInf(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession session = req.getSession();
        //获取用户id
        Integer userId = (Integer) session.getAttribute("loggedId");
        User loggedUserInf = userService.getUserById(userId);

        session.setAttribute("loggedUserInf", loggedUserInf);

        //设置不弹窗
        session.setAttribute("updatePwdRet", "0");
        session.setAttribute("updateInfRet", "0");

        super.processTemplate("WEB-INF/pages/account/account_inf", req, resp);
    }

    protected void showOwnGameInf(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession session = req.getSession();
        //获取用户id
        Integer userId = (Integer) session.getAttribute("loggedId");

        List<OwnGame> ownGameList = ownGameService.showUserOwnGame(userId);
        List<Game> gameList = new ArrayList<>();
        for (OwnGame ownGame : ownGameList) {
            Game game = gameService.queryGameById(ownGame.getGameId());
            gameList.add(game);
        }

        session.setAttribute("ownGameList", gameList);

        super.processTemplate("WEB-INF/pages/account/account_owngame", req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("op") != null && req.getParameter("op").equals("getAllAccounts")) {
            getAllAccounts(req, resp);
        }

        if (req.getParameter("showOwnGame") != null && req.getParameter("showOwnGame").equals("1")) {
            showOwnGameInf(req, resp);
        }

        if (req.getParameter("op") != null && req.getParameter("op").equals("myAccountInf")) {
            showMyAccountInf(req, resp);
        }
    }

    protected void updateAccountInf(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String newUsername = req.getParameter("newUsername");
        String newEmail = req.getParameter("newEmail");
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");

        //检查用户名是否存在
        if (userService.existsUsername(newUsername)) {
            //存在，不可更改
            req.setAttribute("updateInfRet", '1');
        } else {
            //不存在，可以更改
            userService.updateUnameAndEmail(new User(userId, newUsername, null, newEmail, null));
            req.setAttribute("updateInfRet", '2');
        }

        User loggedUserInf = userService.getUserById(userId);

        req.getSession().setAttribute("loggedUserInf", loggedUserInf);

        super.processTemplate("WEB-INF/pages/account/account_inf", req, resp);
    }

    protected void updateAccountPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String oldPwd = req.getParameter("oldPwd");
        String newPwd = req.getParameter("newPwd");
        Integer userId = (Integer) req.getSession().getAttribute("loggedId");
        String username = (String) req.getSession().getAttribute("loggedUsername");
        User login = userService.login(new User(null, username, oldPwd, null, null));
        if (login == null) {
            //旧密码错误，不予更新
            req.setAttribute("updatePwdRet", '1');
        } else {
            //进行更新
            userService.updatePwd(new User(userId, null, newPwd, null, null));
            req.setAttribute("updatePwdRet", '2');
        }

        User loggedUserInf = userService.getUserById(userId);

        req.getSession().setAttribute("loggedUserInf", loggedUserInf);

        super.processTemplate("WEB-INF/pages/account/account_inf", req, resp);
    }

    protected void getAllAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<User> accountList = userService.getAllUsers();
        request.getSession().setAttribute("accountList", accountList);
        super.processTemplate("WEB-INF/pages/order_admin_page/order_admin_users", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("updateInf") != null && req.getParameter("updateInf").equals("1")) {
            updateAccountInf(req, resp);
        }

        if (req.getParameter("updatePwd") != null && req.getParameter("updatePwd").equals("1")) {
            updateAccountPwd(req, resp);
        }
    }
}
