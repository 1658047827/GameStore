package com.fishandchips.service;

import com.fishandchips.pojo.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    User login(User user);

    boolean existsUsername(String username);

    User getUserById(Integer userId);

    void updateUnameAndEmail(User user);

    void updatePwd(User user);

    List<User> getAllUsers();
}
