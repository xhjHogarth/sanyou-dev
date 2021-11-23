package com.sanyou.service;

import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.utils.PagedResult;

/**
 * User: asus
 * Date: 2021-11-11
 * Time: 1:38
 * Version:V1.0
 */
public interface VerticalityService {
    void updateState(VerticalityData verticalityData);

    VerticalityData query(String code);

    PagedResult query(VerticalityDataVo queryVo, Integer page, Integer pageSize);
}
