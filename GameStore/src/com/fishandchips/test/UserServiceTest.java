package com.fishandchips.test;

import com.fishandchips.pojo.User;
import com.fishandchips.service.UserService;
import com.fishandchips.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"xsy140","666666","xsy140@qq.com",null));
    }

    @Test
    public void login() {
        if(userService.login(new User(null,"admin","1256",null,null))==null){
            System.out.println("登录失败");
        }else{
            System.out.println("登陆成功");
        }
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("aasda")){
            System.out.println("用户名已存在！");
        }else{
            System.out.println("用户名可用！");
        }
    }
}