package com.sanyou.mapper;

import com.sanyou.pojo.RoleResource;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper extends MyMapper<RoleResource> {
    List<RoleResource> checkAuth(@Param("userId") String userId, @Param("url") String url);
}