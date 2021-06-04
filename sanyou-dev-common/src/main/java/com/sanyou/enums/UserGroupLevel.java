package com.sanyou.enums;

/**
 * User: asus
 * Date: 2021/6/1
 * Time: 19:35
 * Version:V1.0
 */
public enum UserGroupLevel {

    MANAGER(1),
    MEMBER(2),
    PARTNER(3);

    private final int value;

    UserGroupLevel(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
