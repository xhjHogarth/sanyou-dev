package com.sanyou.service.impl;

import com.sanyou.mapper.UserEquipmentMapper;
import com.sanyou.pojo.UserEquipment;
import com.sanyou.service.UserEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/14
 * Time: 15:23
 * Version:V1.0
 */
@Service
public class UserEquipmentServiceImpl implements UserEquipmentService {

    @Autowired
    private UserEquipmentMapper userEquipmentMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkAuth(String lineno, String userId) {

        List<UserEquipment> list = userEquipmentMapper.checkAuth(lineno,userId);

        if(list == null || list.size() == 0)
            return false;

        return true;
    }
}
