package com.sanyou.service;

import com.sanyou.pojo.SearchHistory;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-30
 * Time: 14:13
 * Version:V1.0
 */
public interface SearchHistoryService {

    List<SearchHistory> getSearchHistory(String userId);
}
