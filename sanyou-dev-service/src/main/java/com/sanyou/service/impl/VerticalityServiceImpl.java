package com.sanyou.service.impl;

import com.sanyou.mapper.VerticalityDataMapper;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.service.VerticalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * User: asus
 * Date: 2021-11-11
 * Time: 1:38
 * Version:V1.0
 */
@Service
public class VerticalityServiceImpl implements VerticalityService {

    @Autowired
    private VerticalityDataMapper verticalityDataMapper;


    @Override
    public void updateState(VerticalityData verticalityData) {
        Example example = new Example(VerticalityData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("verticalityId",verticalityData.getVerticalityId());

        VerticalityData data = new VerticalityData();
        if(verticalityData.getState() != null)
            data.setState(verticalityData.getState());
        if(verticalityData.getMaintainType() != null)
            data.setMaintainType(verticalityData.getMaintainType());

        verticalityDataMapper.updateByExampleSelective(data,example);
    }
}
