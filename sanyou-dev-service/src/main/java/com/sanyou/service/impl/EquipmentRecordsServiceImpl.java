package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.EquipmentRecordsMapper;
import com.sanyou.mapper.UserMapper;
import com.sanyou.pojo.User;
import com.sanyou.pojo.vo.EquipmentRecordsVo;
import com.sanyou.service.EquipmentRecordsService;
import com.sanyou.utils.PagedResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/6
 * Time: 1:29
 * Version:V1.0
 */
@Service
public class EquipmentRecordsServiceImpl implements EquipmentRecordsService {

    @Autowired
    private EquipmentRecordsMapper equipmentRecordsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<EquipmentRecordsVo> list = equipmentRecordsMapper.query(query);

        for (EquipmentRecordsVo vo : list) {
            String userId = vo.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            if(user != null)
                vo.setUsername(user.getUsername());
        }


        PageInfo<EquipmentRecordsVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }
}
