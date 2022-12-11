package com.yuimorii.onlineBookStore.dao.impl;

import com.yuimorii.onlineBookStore.dao.UserDao;
import com.yuimorii.onlineBookStore.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return (User) queryForOne(User.class, sql, username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`)values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
