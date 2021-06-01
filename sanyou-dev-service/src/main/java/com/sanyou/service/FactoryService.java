package com.sanyou.service;

import com.sanyou.pojo.Factory;

import java.util.List;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 1:44
 * Version:V1.0
 */
public interface FactoryService {

    /**
     * 根据厂家名查找厂家
     * @param factoryName 厂家名
     * @return
     */
    Factory findByName(String factoryName);

    /**
     * 根据厂家id查找厂家
     * @param id 厂家id
     * @return
     */
    Factory findById(String id);

    /**
     * 新增厂家
     * @param factoryName 厂家名
     */
    void addFactory(String factoryName);

    /**
     * 修改厂家信息
     * @param factory
     */
    void updateFactoryInfo(Factory factory);

    /**
     * 查询厂家
     * @param factoryName
     * @return
     */
    List<Factory> query(String factoryName);
}
