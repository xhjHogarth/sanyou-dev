package com.sanyou.service;

import com.sanyou.pojo.UserSession;

/**
 * User: asus
 * Date: 2021/6/12
 * Time: 19:33
 * Version:V1.0
 */
public interface UserSessionService {


    /**
     * 保存或更新session
     * @param userSession
     */
    void updateSession(UserSession userSession);

}
