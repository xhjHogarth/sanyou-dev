package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.FactoryMapper;
import com.sanyou.pojo.Factory;
import com.sanyou.pojo.vo.FactoryVo;
import com.sanyou.service.FactoryService;
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
    public void addFactory(Factory factory) {
        String id = sid.nextShort();
        factory.setId(id);
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
        if(factory.getDeleteMark() != null && factory.getDeleteMark() == 1){
            factory.setDeletetime(new Date());
            if(factory.getLayer() != null && factory.getLayer() == 1){
                Example example2 = new Example(Factory.class);
                Example.Criteria criteria2 = example2.createCriteria();
                criteria2.andEqualTo("parentId",factory.getId());

                Factory factory2 = new Factory();
                factory2.setDeleteMark((byte)1);
                factoryMapper.updateByExampleSelective(factory2,example2);
            }
        }

        factoryMapper.updateByExampleSelective(factory,example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<FactoryVo> factoryList = factoryMapper.query(query);

        int index = (page-1) * pageSize + 1;
        for (FactoryVo factoryVo : factoryList) {
            factoryVo.setIndex(index++);

            Example example = new Example(Factory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",factoryVo.getId());
            criteria.andEqualTo("deleteMark",0);
            criteria.andEqualTo("layer",2);
            Factory factory = factoryMapper.selectOneByExample(example);
            if(factory != null){
                factoryVo.setHasChildren(true);
            }else{
                factoryVo.setHasChildren(false);
            }
        }

        PageInfo<FactoryVo> pageList = new PageInfo<>(factoryList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(factoryList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Factory> querySubFactory(String parentId) {

        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        criteria.andEqualTo("deleteMark",0);
        criteria.andEqualTo("layer",2);
        List<Factory> factories = factoryMapper.selectByExample(example);

        return factories;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Factory> getAll() {

        Example example = new Example(Factory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteMark",0);
        criteria.andEqualTo("layer",1);
        List<Factory> factoryList = factoryMapper.selectByExample(example);

        return factoryList;
    }
}
