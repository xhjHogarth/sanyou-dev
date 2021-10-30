package com.sanyou.service.impl;

import com.sanyou.mapper.SearchHistoryMapper;
import com.sanyou.pojo.SearchHistory;
import com.sanyou.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-30
 * Time: 14:13
 * Version:V1.0
 */
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> getSearchHistory(String userId) {

        Example example = new Example(SearchHistory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<SearchHistory> historyList = searchHistoryMapper.selectByExample(example);
        return historyList;
    }
}
