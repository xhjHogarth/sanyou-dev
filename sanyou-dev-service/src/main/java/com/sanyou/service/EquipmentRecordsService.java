package com.sanyou.service;

import com.sanyou.utils.PagedResult;

/**
 * User: asus
 * Date: 2021/6/6
 * Time: 1:29
 * Version:V1.0
 */
public interface EquipmentRecordsService {
    /**
     * 分页查看设备参数修改记录列表
     * @param query
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult query(String query, Integer page, Integer pageSize);
}
