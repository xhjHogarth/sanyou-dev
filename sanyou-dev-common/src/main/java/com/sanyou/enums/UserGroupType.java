package com.sanyou.enums;

/**
 * User: asus
 * Date: 2021/6/1
 * Time: 19:26
 * Version:V1.0
 */
public enum UserGroupType {

    SYSTEM(1),
    CUSTOM(2);

    private final int value;

    UserGroupType(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
