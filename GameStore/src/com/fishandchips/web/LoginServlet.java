package com.fishandchips.web;

import com.fishandchips.pojo.User;
import com.fishandchips.service.UserService;
import com.fishandchips.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("tologin") != null && req.getParameter("tologin").equals("1")) {
            super.processTemplate("WEB-INF/pages/login.html", req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2、检查用户
        User user = userService.login(new User(null, username, password, null, null));
        if (user == null) {
            //失败，回去
            super.processTemplate("WEB-INF/pages/login", req, resp);
        } else {
            //登录成功，前往 首页
            HttpSession session = req.getSession();
            session.setAttribute("isLoggedin", 1);
            session.setAttribute("loggedId", user.getId());
            session.setAttribute("loggedUsername", username);
            session.setAttribute("accountRole",user.getRole()); //0运营管理员 1用户 2游戏管理员

            System.out.println(user);

            //重定向
            resp.sendRedirect("/GameStore/index");
        }
    }
}
