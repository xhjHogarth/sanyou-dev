package com.sanyou.mapper;

import com.sanyou.pojo.User;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    List<UserVo> query(@Param("user") UserVo userVo);
}