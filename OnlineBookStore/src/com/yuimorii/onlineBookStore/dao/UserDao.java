package com.yuimorii.onlineBookStore.dao;

import com.yuimorii.onlineBookStore.dao.impl.BaseDao;
import com.yuimorii.onlineBookStore.pojo.User;

public interface UserDao {
    /**
     * 根據用戶名查詢用戶信息
     * @param username
     * @return null， 說明沒有這個用戶，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用戶信息
     * @param user
     * @return
     */
    public int saveUser(User user);


    /**
     * 根據用戶名和密碼查詢用戶信息
     * @param username
     * @param password
     * @return null， 說明沒有這個用戶，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);




}
