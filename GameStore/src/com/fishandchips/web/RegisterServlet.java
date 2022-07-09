package com.fishandchips.web;

import com.fishandchips.pojo.User;
import com.fishandchips.service.UserService;
import com.fishandchips.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register.do")
public class RegisterServlet extends ViewBaseServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2、检查用户名是否可用
        if (userService.existsUsername(username)) {
            System.out.println("用户名" + username + "已存在");
            super.processTemplate("WEB-INF/pages/register",req,resp);
        } else {
            userService.registerUser(new User(null, username, password, email, null));
            //跳到首页或者注册成功页面
            super.processTemplate("WEB-INF/pages/register_success",req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("toreg")!=null && req.getParameter("toreg").equals("1")){
            super.processTemplate("WEB-INF/pages/register",req,resp);
        }
    }
}
