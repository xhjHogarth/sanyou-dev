package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.*;
import com.sanyou.pojo.*;
import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.service.OrderService;
import com.sanyou.utils.PagedResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-12-13
 * Time: 15:39
 * Version:V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkOrderExist(OrderVo orderVo) {
        int count = orderMapper.checkOrderExist(orderVo);
        return count>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addOrder(OrderVo orderVo) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVo,order);

        order.setCreatetime(new Date());
        order.setDeleteMark((byte)0);

        if(orderVo.getProjectId() != null){
            Project project = projectMapper.selectByPrimaryKey(orderVo.getProjectId());
            if(order.getDdbLength() == null)
                order.setDdbLength(project.getDdbLength());
            if(order.getDdbWidth() == null)
                order.setDdbWidth(project.getDdbWidth());
            if(order.getDdbHeight() == null)
                order.setDdbHeight(project.getDdbHeight());
            if(order.getYjbLength() == null)
                order.setYjbLength(project.getYjbLength());
            if(order.getYjbWidth() == null)
                order.setYjbWidth(project.getYjbWidth());
            if(order.getYjbHeight() == null)
                order.setYjbHeight(project.getYjbHeight());
        }

        orderMapper.insertOne(order);

        Integer id = order.getId();
        ProjectOrder projectOrder = new ProjectOrder();
        projectOrder.setOrderId(id);
        projectOrder.setProjectId(orderVo.getProjectId());
        projectOrderMapper.insert(projectOrder);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(Integer page, Integer pageSize,OrderVo queryVo) {
        PageHelper.startPage(page,pageSize);
        List<OrderVo> list = orderMapper.queryOrders(queryVo);

        for (OrderVo orderVo : list) {
            if(orderVo.getDdbLength() != null
            && orderVo.getDdbWidth() != null
            && orderVo.getDdbHeight() != null)
                orderVo.setDdbSize(orderVo.getDdbLength() + "*" + orderVo.getDdbWidth() + "*" + orderVo.getDdbHeight());

            if(orderVo.getYjbLength() != null
                    && orderVo.getYjbWidth() != null
                    && orderVo.getYjbHeight() != null)
                orderVo.setYjbSize(orderVo.getYjbLength() + "*" + orderVo.getYjbWidth() + "*" + orderVo.getYjbHeight());
        }

        PageInfo<OrderVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrder(OrderVo orderVo) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVo,order);
        order.setUpdatetime(new Date());
        order.setDeleteMark((byte)0);
        orderMapper.updateOrder(order);

        Example example = new Example(ProjectOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",order.getId());
        projectOrderMapper.deleteByExample(example);

        ProjectOrder projectOrder = new ProjectOrder();
        projectOrder.setProjectId(orderVo.getProjectId());
        projectOrder.setOrderId(order.getId());
        projectOrderMapper.insert(projectOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteOrder(Integer oid) {
        orderMapper.deleteOrder(oid);

        //删除分配的阴极板
        Example example = new Example(OrderProduct.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId",oid);
        orderProductMapper.deleteByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderVo> getProjectOrder(Integer pid) {
        List<OrderVo> orderVoList = orderMapper.getProjectOrder(pid);
        for (OrderVo orderVo : orderVoList) {
            if(orderVo.getDdbLength() != null
                    && orderVo.getDdbWidth() != null
                    && orderVo.getDdbHeight() != null)
                orderVo.setDdbSize(orderVo.getDdbLength() + "*" + orderVo.getDdbWidth() + "*" + orderVo.getDdbHeight());

            if(orderVo.getYjbLength() != null
                    && orderVo.getYjbWidth() != null
                    && orderVo.getYjbHeight() != null)
                orderVo.setYjbSize(orderVo.getYjbLength() + "*" + orderVo.getYjbWidth() + "*" + orderVo.getYjbHeight());
        }
        return orderVoList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void distrProduct(OrderVo orderVo) {
        Example example1 = new Example(OrderProduct.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("orderId",orderVo.getId());
        orderProductMapper.deleteByExample(example1);

        Example example2 = new Example(Product.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andBetween("productCode",orderVo.getStartProductCode(),orderVo.getEndProductCode());
        List<Product> products = productMapper.selectByExample(example2);

        for (Product product : products) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(orderVo.getId());
            orderProduct.setProductId(product.getId());
            orderProduct.setCreatetime(new Date());
            orderProductMapper.insert(orderProduct);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult getOrderProduct(Integer oid, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ProductVo> list = productMapper.getOrderProduct(oid);

        for (ProductVo productVo : list) {
            if(productVo.getDdbLength() != null
                    && productVo.getDdbWidth() != null
                    && productVo.getDdbHeight() != null)
                productVo.setDdbSize(productVo.getDdbLength() + "*" + productVo.getDdbWidth() + "*" + productVo.getDdbHeight());

            if(productVo.getYjbLength() != null
                    && productVo.getYjbWidth() != null
                    && productVo.getYjbHeight() != null)
                productVo.setYjbSize(productVo.getYjbLength() + "*" + productVo.getYjbWidth() + "*" + productVo.getYjbHeight());
        }

        PageInfo<ProductVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkCodeRange(String startProductCode, String endProductCode) {
        int count = productMapper.checkCodeRange(startProductCode,endProductCode);
        return count>0;
    }
}
