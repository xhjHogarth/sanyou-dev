package com.sanyou.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.*;
import com.sanyou.pojo.*;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.service.UserService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
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
 * Date: 2021/5/25
 * Time: 21:38
 * Version:V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsergroupMapper usergroupMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private SAdministrativeDivisionsMapper divisionsMapper;

    @Autowired
    private UserEquipmentMapper userEquipmentMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private Sid sid;

    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("deleteMark",0);
        User user = userMapper.selectOneByExample(userExample);
        if(user != null)
            return true;
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUser(User user) {
        String id = sid.nextShort();

        user.setId(id);

        userMapper.insert(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String password) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        User user = userMapper.selectOneByExample(userExample);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void enableOrUnEnableUser(String userId, int mark) {
        User user = new User();
        user.setId(userId);
        user.setEnableMark((byte)mark);
        user.setUpdatetime(new Date());

        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",userId);
        userMapper.updateByExampleSelective(user,userExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserInfo(List<User> users) {

        for (User user : users) {
            Example userExample = new Example(User.class);
            Example.Criteria criteria = userExample.createCriteria();
            criteria.andEqualTo("id",user.getId());

            user.setUpdatetime(new Date());
            if(user.getDeleteMark()!=null && user.getDeleteMark()==1)
                user.setDeletetime(new Date());

            userMapper.updateByExampleSelective(user,userExample);
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(UserVo userVo, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<UserVo> list = userMapper.query(userVo);

        for (UserVo vo : list) {
            String groupId = vo.getGroupId();
            if(StringUtils.isNotBlank(groupId)) {
                Usergroup usergroup = usergroupMapper.selectByPrimaryKey(groupId);
                if (usergroup != null)
                    vo.setGroupName(usergroup.getGroupName());
            }
            String factoryId = vo.getFactoryId();
            if(StringUtils.isNotBlank(factoryId)){
                Factory factory = factoryMapper.selectByPrimaryKey(factoryId);
                if(factory != null)
                    vo.setFactoryName(factory.getFactoryName());
            }
            String subFactoryId = vo.getSubFactoryId();
            if(StringUtils.isNotBlank(subFactoryId)){
                Factory factory = factoryMapper.selectByPrimaryKey(subFactoryId);
                if(factory != null)
                    vo.setSubFactoryName(factory.getFactoryName());
            }

            if(vo.getSex() != null){
                if(vo.getSex() == 1){
                    vo.setSexName("保密");
                }else if(vo.getSex() == 2){
                    vo.setSexName("男");
                }else if(vo.getSex() == 3){
                    vo.setSexName("女");
                }
            }

            if(vo.getArea() != null){
                SAdministrativeDivisions administrativeDivisions = divisionsMapper.selectByPrimaryKey(vo.getArea());
                String fullname = administrativeDivisions.getFullname();
                vo.setAdministration(fullname.replace('/',','));
            }
        }

        PageInfo<UserVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryIsExist(String id) {
        User user = userMapper.selectByPrimaryKey(id);

        return user==null?false:true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void assignEquip(List<UserEquipment> userEquipments,String userId) {

        Example example = new Example(UserEquipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        userEquipmentMapper.deleteByExample(example);

        for (UserEquipment userEquipment : userEquipments) {
            userEquipment.setCreatetime(new Date());
        }
        userEquipmentMapper.insertList(userEquipments);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkAuth(String userId, String url) {

        List<RoleResource> auths = roleResourceMapper.checkAuth(userId,url);

        if(auths != null && auths.size() > 0)
            return true;

        return false;
    }
}
