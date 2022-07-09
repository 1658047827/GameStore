package com.fishandchips.test;

import com.fishandchips.dao.UserDao;
import com.fishandchips.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","123456")==null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("success");
        }
    }

    @Test
    public void saveUser() {
    }
}