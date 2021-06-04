package com.sanyou.service;

import com.sanyou.pojo.Role;
import com.sanyou.pojo.Usergroup;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/1
 * Time: 17:37
 * Version:V1.0
 */
public interface UserGroupService {

    /**
     * 新增用户组
     * @param userGroup
     */
    void addGroup(Usergroup userGroup);

    /**
     * 分页查询用户组信息
     * @param query
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult query(String query, Integer page, Integer pageSize);

    /**
     * 修改用户组信息
     * @param usergroup
     */
    void updateUserGroup(Usergroup usergroup);

    /**
     * 查询用户组所拥有的角色
     * @param groupId
     * @return
     */
    List<Role> queryRoles(String groupId);

    /**
     * 分配用户组角色
     * @param roles
     * @param groupId
     */
    void assignRoles(List<Role> roles, String groupId);

    /**
     * 获取所有的用户组
     * @return
     */
    List<Usergroup> getAll();

}
