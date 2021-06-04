package com.sanyou.mapper;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.Role;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> queryRoles(@Param("query") String query);

    List<Resource> queryAuth(String roleId);
}