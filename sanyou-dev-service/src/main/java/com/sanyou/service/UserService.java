package com.sanyou.service;


import com.sanyou.pojo.User;
import com.sanyou.pojo.UserEquipment;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

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
    User queryUsernameIsExist(String username);

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
     */
    void updateUserInfo(List<User> users);

    /**
     * 分页查询用户列表
     * @param userVo
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult query(UserVo userVo, Integer page, Integer pageSize);

    /**
     * 根据用户id判断用户是否存在
     * @param id 用户id
     * @return
     */
    boolean queryIsExist(String id);

    /**
     * 分配用户设备权限
     * @param userEquipments
     * @param userId
     */
    void assignEquip(List<UserEquipment> userEquipments,String userId);

    /**
     * 判断当前用户是否有权限
     * @param userId
     * @param url
     * @return
     */
    boolean checkAuth(String userId, String url);

    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 根据用户id查找用户
     * @param idList
     * @return
     */
    List<UserVo> getUserByIds(String[] idList);
}
