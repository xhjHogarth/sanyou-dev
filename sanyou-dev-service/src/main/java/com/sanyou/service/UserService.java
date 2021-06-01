package com.sanyou.service;

import com.sanyou.pojo.User;

/**
 * User: asus
 * Date: 2021/5/25
 * Time: 21:38
 * Version:V1.0
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 新增用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User queryUserForLogin(String username, String password);

    /**
     * 启用或禁用用户
     * @param userId 用户id
     * @param mark 启用/禁用标志
     */
    public void enableOrUnEnableUser(String userId, int mark);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 返回修改之后的用户信息
     */
    public User updateUserInfo(User user);
}
