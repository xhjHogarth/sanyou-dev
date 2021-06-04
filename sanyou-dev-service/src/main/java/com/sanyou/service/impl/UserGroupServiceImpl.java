package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.enums.UserGroupType;
import com.sanyou.mapper.UsergroupMapper;
import com.sanyou.mapper.UsergroupRoleMapper;
import com.sanyou.pojo.Role;
import com.sanyou.pojo.Usergroup;
import com.sanyou.pojo.UsergroupRole;
import com.sanyou.pojo.vo.UserGroupVo;
import com.sanyou.service.UserGroupService;
import com.sanyou.utils.PagedResult;
import com.sanyou.utils.UserGroupUtil;
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
 * Date: 2021/6/1
 * Time: 17:37
 * Version:V1.0
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UsergroupMapper usergroupMapper;

    @Autowired
    private UsergroupRoleMapper usergroupRoleMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addGroup(Usergroup usergroup) {
        String id = sid.nextShort();
        usergroup.setId(id);
        usergroup.setGroupType((byte)UserGroupType.CUSTOM.getValue());
        usergroup.setCreatetime(new Date());
        usergroup.setDeleteMark((byte)0);
        usergroupMapper.insert(usergroup);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<UserGroupVo> list = usergroupMapper.queryUserGroups(query);

        int index = (page-1) * pageSize + 1;
        for (UserGroupVo userGroupVo : list) {
            userGroupVo.setIndex(index++);
            userGroupVo.setTypeName(UserGroupUtil.getTypeName(userGroupVo.getGroupType()));
            userGroupVo.setLevelName(UserGroupUtil.getLevelName(userGroupVo.getGroupLevel()));

            String groupId = userGroupVo.getId();
            List<Role> roles = usergroupMapper.queryRoles(groupId);
            String rolesName = "";
            for (int i =0;i<roles.size();i++) {
                rolesName += roles.get(i).getTitle();
                if(i < roles.size() - 1)
                    rolesName += ",";
            }
            userGroupVo.setRolesName(rolesName);
        }

        PageInfo<UserGroupVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserGroup(Usergroup usergroup) {

        Example example = new Example(Usergroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",usergroup.getId());

        usergroup.setUpdatetime(new Date());
        usergroupMapper.updateByExampleSelective(usergroup,example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Role> queryRoles(String groupId) {

        List<Role> list = usergroupMapper.queryRoles(groupId);

        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void assignRoles(List<Role> roles, String groupId) {
        Example example = new Example(UsergroupRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usergroupid",groupId);
        List<UsergroupRole> usergroupRoles = usergroupRoleMapper.selectByExample(example);

        for (UsergroupRole usergroupRole : usergroupRoles) {
            usergroupRoleMapper.delete(usergroupRole);
        }

        for (Role role : roles) {
            UsergroupRole usergroupRole = new UsergroupRole();
            String id = sid.nextShort();
            usergroupRole.setId(id);
            usergroupRole.setRoleid(role.getId());
            usergroupRole.setUsergroupid(groupId);
            usergroupRole.setCreatetime(new Date());
            usergroupRoleMapper.insert(usergroupRole);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Usergroup> getAll() {

        Example example = new Example(Usergroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteMark",0);
        List<Usergroup> userGroups = usergroupMapper.selectByExample(example);

        return userGroups;
    }
}
