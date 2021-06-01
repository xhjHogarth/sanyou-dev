package com.sanyou.service.impl;

import com.sanyou.mapper.FactoryMapper;
import com.sanyou.pojo.Factory;
import com.sanyou.service.FactoryService;
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
 * Time: 1:44
 * Version:V1.0
 */
@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Factory findByName(String factoryName) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("factoryName",factoryName);
        criteria.andEqualTo("deleteMark", 0);

        return factoryMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Factory findById(String id) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);

        return factoryMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addFactory(String factoryName) {
        Factory factory = new Factory();

        String id = sid.nextShort();
        factory.setId(id);
        factory.setFactoryName(factoryName);
        factory.setCreatetime(new Date());
        factory.setUpdatetime(new Date());
        factory.setDeleteMark((byte)0);

        factoryMapper.insert(factory);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateFactoryInfo(Factory factory) {
        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",factory.getId());

        factory.setUpdatetime(new Date());
        if(factory.getDeleteMark() == 1)
            factory.setDeletetime(new Date());
        factoryMapper.updateByExampleSelective(factory,example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Factory> query(String factoryName) {
        return factoryMapper.query(factoryName);
    }
}
