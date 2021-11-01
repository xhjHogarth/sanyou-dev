package com.sanyou.service;

import com.sanyou.pojo.CollectHistory;

import java.util.List;

/**
 * User: asus
 * Date: 2021-11-01
 * Time: 17:08
 * Version:V1.0
 */
public interface CollectHistoryService {
    List<CollectHistory> getCollectHistory(String userId);

    void collect(CollectHistory collectHistory);

    void unCollect(CollectHistory collectHistory);
}
