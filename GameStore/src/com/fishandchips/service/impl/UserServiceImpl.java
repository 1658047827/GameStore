package com.fishandchips.service.impl;

import com.fishandchips.dao.UserDao;
import com.fishandchips.dao.impl.UserDaoImpl;
import com.fishandchips.pojo.User;
import com.fishandchips.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUname(), user.getPwd());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.queryUserById(userId);
    }

    @Override
    public void updateUnameAndEmail(User user) {
        userDao.updateUnameAndEmail(user);
    }

    @Override
    public void updatePwd(User user) {
        userDao.updatePwd(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
