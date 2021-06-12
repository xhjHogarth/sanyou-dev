package com.sanyou.service.impl;

import com.sanyou.mapper.UserSessionMapper;
import com.sanyou.pojo.UserSession;
import com.sanyou.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * User: asus
 * Date: 2021/6/12
 * Time: 19:33
 * Version:V1.0
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    private UserSessionMapper userSessionMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateSession(UserSession userSession) {
        Example example = new Example(UserSession.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userSession.getUserId());
        UserSession userSession1 = userSessionMapper.selectOneByExample(example);

        if(userSession1 == null){
            userSessionMapper.insert(userSession);
        }else{
            userSession1.setSession(userSession.getSession());
            userSession1.setCreatetime(new Date());
            userSessionMapper.updateByPrimaryKeySelective(userSession1);
        }
    }
}
