package com.fishandchips.dao.impl;

import com.fishandchips.dao.BaseDao;
import com.fishandchips.dao.UserDao;
import com.fishandchips.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,uname,pwd,email from user where uname=?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where uname=? and pwd=?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(uname, pwd, email) values(?,?,?)";
        return update(sql, user.getUname(), user.getPwd(), user.getEmail());
    }

    @Override
    public User queryUserById(Integer id) {
        String sql = "select * from user where id=?";
        return queryForOne(User.class, sql, id);
    }

    @Override
    public int updateUnameAndEmail(User user) {
        String sql = "update user set uname=?, email=? where id=?";
        return update(sql, user.getUname(), user.getEmail(), user.getId());
    }

    @Override
    public int updatePwd(User user) {
        String sql = "update user set pwd=? where id=?";
        return update(sql, user.getPwd(), user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from user";
        return queryForList(User.class, sql);
    }
}
