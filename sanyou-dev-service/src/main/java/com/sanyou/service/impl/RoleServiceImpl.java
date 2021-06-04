package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.RoleMapper;
import com.sanyou.mapper.RoleResourceMapper;
import com.sanyou.pojo.Resource;
import com.sanyou.pojo.Role;
import com.sanyou.pojo.RoleResource;
import com.sanyou.service.RoleService;
import com.sanyou.utils.PagedResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 20:41
 * Version:V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private Sid sid;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Role findByCodeOrTitle(Role role) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteMark", 0);
        criteria.andEqualTo("roleCode",role.getRoleCode());
        criteria.orEqualTo("title",role.getTitle());
        Role role1 = roleMapper.selectOneByExample(example);
        return role1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addRole(Role role) {
        String id = sid.nextShort();
        role.setId(id);
        role.setCreatetime(new Date());
        role.setDeleteMark((byte)0);
        roleMapper.insert(role);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateRoles(List<Role> roles) {
        for (Role role : roles) {
            Example example = new Example(Role.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",role.getId());

            role.setUpdatetime(new Date());
            roleMapper.updateByExampleSelective(role,example);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<Role> list = roleMapper.queryRoles(query);
        PageInfo<Role> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Resource> queryAuth(String roleId) {

        List<Resource> list = roleMapper.queryAuth(roleId);

        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void assignAuth(List<Resource> resources, String roleId) {

        Example example = new Example(RoleResource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleid",roleId);
        List<RoleResource> roleResources = roleResourceMapper.selectByExample(example);

        for (RoleResource roleResource : roleResources) {
            roleResourceMapper.delete(roleResource);
        }

        for (Resource resource : resources) {
            RoleResource roleResource = new RoleResource();
            String id = sid.nextShort();
            roleResource.setId(id);
            roleResource.setResourceid(resource.getId());
            roleResource.setRoleid(roleId);
            roleResource.setCreatetime(new Date());
            roleResourceMapper.insert(roleResource);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Role> getAll() {

        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteMark",0);
        List<Role> roles = roleMapper.selectByExample(example);

        return roles;
    }
}
