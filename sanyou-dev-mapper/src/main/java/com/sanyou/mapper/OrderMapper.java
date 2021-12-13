package com.sanyou.mapper;

import com.sanyou.pojo.Order;
import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.utils.MyMapper;

import java.util.List;

public interface OrderMapper extends MyMapper<Order> {
    int checkOrderExist(OrderVo orderVo);

    void insertOne(Order order);

    List<OrderVo> queryOrders(OrderVo orderVo);

    void updateOrder(Order order);

    void deleteOrder(Integer oid);

    List<OrderVo> getProjectOrder(Integer pid);
}