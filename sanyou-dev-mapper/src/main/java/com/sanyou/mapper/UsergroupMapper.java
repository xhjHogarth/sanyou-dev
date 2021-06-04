package com.sanyou.mapper;

import com.sanyou.pojo.Role;
import com.sanyou.pojo.Usergroup;
import com.sanyou.pojo.vo.UserGroupVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsergroupMapper extends MyMapper<Usergroup> {
    List<UserGroupVo> queryUserGroups(@Param("query") String query);

    List<Role> queryRoles(String groupId);
}