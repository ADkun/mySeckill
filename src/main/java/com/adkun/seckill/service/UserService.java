package com.adkun.seckill.service;


import com.adkun.seckill.entity.User;

/**
 * 用户相关
 * @author adkun
 */
public interface UserService {

    /**
     * 注册
     * @param user
     */
    void register(User user);

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    User login(String phone, String password);

    /**
     * 根据用户ID查找用户
     * @param id
     * @return
     */
    User findUserById(int id);
}
