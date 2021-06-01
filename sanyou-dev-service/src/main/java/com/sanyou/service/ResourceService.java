package com.sanyou.service;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.vo.ResourceVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 14:29
 * Version:V1.0
 */
public interface ResourceService {

    /**
     * 新增资源
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * 根据资源编码查找资源
     * @param resCode
     * @return
     */
    Resource findByName(String resCode);

    /**
     * 分页查询资源
     * @param query 查询条件
     * @param page 页码
     * @param pageSize 每页条数
     * @return
     */
    PagedResult query(String query, Integer page, Integer pageSize);

    /**
     * 批量更新资源
     * @param list
     */
    void updateResource(List<Resource> list);

    /**
     * 获取
     * @return
     */
    List<ResourceVo> getAll();
}
