package com.sanyou.mapper;

import com.sanyou.pojo.UserLog;
import com.sanyou.pojo.vo.UserLogVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLogMapper extends MyMapper<UserLog> {


    List<UserLogVo> query(@Param("userLogVo") UserLogVo userLogVo);
}