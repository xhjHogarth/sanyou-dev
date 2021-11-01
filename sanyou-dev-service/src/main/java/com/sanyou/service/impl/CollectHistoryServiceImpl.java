package com.sanyou.service.impl;

import com.sanyou.mapper.CollectHistoryMapper;
import com.sanyou.pojo.CollectHistory;
import com.sanyou.service.CollectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * User: asus
 * Date: 2021-11-01
 * Time: 17:09
 * Version:V1.0
 */
@Service
public class CollectHistoryServiceImpl implements CollectHistoryService {

    @Autowired
    private CollectHistoryMapper collectHistoryMapper;

    @Override
    public List<CollectHistory> getCollectHistory(String userId) {
        Example example = new Example(CollectHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<CollectHistory> historyList = collectHistoryMapper.selectByExample(example);
        return historyList;
    }

    @Override
    public void collect(CollectHistory collectHistory) {
        collectHistoryMapper.insertSelective(collectHistory);
    }

    @Override
    public void unCollect(CollectHistory collectHistory) {
        Example example = new Example(CollectHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",collectHistory.getUserId());
        criteria.andEqualTo("collectCode",collectHistory.getCollectCode());
        collectHistoryMapper.deleteByExample(example);
    }
}
