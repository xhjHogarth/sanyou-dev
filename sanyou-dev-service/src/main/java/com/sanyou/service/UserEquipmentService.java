package com.sanyou.service;

/**
 * User: asus
 * Date: 2021/6/14
 * Time: 15:23
 * Version:V1.0
 */
public interface UserEquipmentService {

    /**
     * 判断用户是否拥有设备数据权限
     * @param lineno
     * @param userId
     * @return
     */
    boolean checkAuth(String lineno, String userId);
}
