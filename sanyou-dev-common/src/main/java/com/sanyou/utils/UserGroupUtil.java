package com.sanyou.utils;

/**
 * User: asus
 * Date: 2021/6/1
 * Time: 20:02
 * Version:V1.0
 */
public class UserGroupUtil {

    public static String getTypeName(byte type){
        if(type == 1)
            return "系统组";
        else if(type == 2)
            return "自定义";
        return "";
    }

    public static String getLevelName(byte level){
        if(level == 1)
            return "管理员";
        else if(level == 2)
            return "会员组";
        else if(level == 3)
            return "合作伙伴";

        return "";
    }
}
