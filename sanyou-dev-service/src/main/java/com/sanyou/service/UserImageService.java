package com.sanyou.service;

import com.sanyou.pojo.UserImage;

/**
 * User: asus
 * Date: 2021-11-19
 * Time: 13:39
 * Version:V1.0
 */
public interface UserImageService {
    void add(UserImage userImage);

    UserImage get(String userId);
}
