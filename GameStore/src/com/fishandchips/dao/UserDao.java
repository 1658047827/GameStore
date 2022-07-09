package com.fishandchips.dao;

import com.fishandchips.pojo.User;

import java.util.List;

public interface UserDao{
    /* 根据用户名查询 */
    User queryUserByUsername(String username);

    /* 根据用户名和密码查询 */
    User queryUserByUsernameAndPassword(String username, String password);

    /* 保存用户信息 */
    int saveUser(User user);

    /* 根据用户id查询 */
    User queryUserById(Integer id);

    /* 更新用户信息 */
    int updateUnameAndEmail(User user);

    /* 更新用户密码 */
    int updatePwd(User user);

    /* 获取所有的用户信息 */
    List<User> getAllUsers();
}
