package com.sanyou.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * User: asus
 * Date: 2021/5/6
 * Time: 20:48
 * Version:V1.0
 */
@RestController
public class BasicController {

    public static final String USER_REDIS_SESSION = "user-redis_session";

    //每页分页的记录数
    public static final Integer PAGE_SIZE = 10;
}
