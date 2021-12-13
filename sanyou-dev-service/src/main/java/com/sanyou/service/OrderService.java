package com.sanyou.service;

import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021-12-13
 * Time: 15:38
 * Version:V1.0
 */
public interface OrderService {
    boolean checkOrderExist(OrderVo orderVo);

    void addOrder(OrderVo orderVo);

    PagedResult query(Integer page, Integer pageSize,OrderVo orderVo);

    void updateOrder(OrderVo orderVo);

    void deleteOrder(Integer oid);

    List<OrderVo> getProjectOrder(Integer pid);

    void distrProduct(OrderVo orderVo);

    PagedResult getOrderProduct(Integer oid, Integer page, Integer pageSize);

    boolean checkCodeRange(String startProductCode, String endProductCode);
}
