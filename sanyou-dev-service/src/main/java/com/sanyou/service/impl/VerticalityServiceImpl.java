package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.VerticalityDataMapper;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.service.VerticalityService;
import com.sanyou.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateState(VerticalityData verticalityData) {
        Example example = new Example(VerticalityData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("verticalityId",verticalityData.getVerticalityId());

        VerticalityData data = new VerticalityData();

        if(verticalityData.getState() != null) {
            data.setState(verticalityData.getState());
            data.setUpdateStateDate(new Date());
        }
        if(verticalityData.getMaintainType() != null) {
            data.setMaintainType(verticalityData.getMaintainType());
            data.setUpdateMaintainDate(new Date());
            data.setUserid(verticalityData.getUserid());
        }

        verticalityDataMapper.updateByExampleSelective(data,example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public VerticalityData query(String code) {

        Example example = new Example(VerticalityData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("verticalityId",code);

        VerticalityData verticalityData = verticalityDataMapper.selectOneByExample(example);

        return verticalityData;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(VerticalityDataVo queryVo, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<VerticalityDataVo> dataList = verticalityDataMapper.query(queryVo);

        for (VerticalityDataVo vo : dataList) {
            if(vo.getState() == null || vo.getState() != 1){
                vo.setMaintainType(null);
                vo.setUpdateMaintainDate(null);
            }
        }

        PageInfo<VerticalityDataVo> pageList = new PageInfo<>(dataList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(dataList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }
}
