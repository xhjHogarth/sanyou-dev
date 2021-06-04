package com.sanyou.service;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.Role;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 20:41
 * Version:V1.0
 */
public interface RoleService {

    /**
     * 根据角色编码或标题查找角色
     * @param role
     * @return
     */
    Role findByCodeOrTitle(Role role);

    /**
     * 新增角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 批量修改角色
     * @param roles
     */
    void updateRoles(List<Role> roles);

    /**
     * 分页查询资源
     * @param query 查询条件
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
    PagedResult query(String query, Integer page, Integer pageSize);

    /**
     * 获取角色权限
     * @param roleId
     * @return
     */
    List<Resource> queryAuth(String roleId);

    /**
     * 分配角色权限
     * @param resources 权限
     * @param roleId 角色id
     */
    void assignAuth(List<Resource> resources, String roleId);

    /**
     * 获取所有的角色
     * @return
     */
    List<Role> getAll();
}
