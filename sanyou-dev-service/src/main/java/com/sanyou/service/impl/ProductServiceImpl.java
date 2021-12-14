package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.ProductMapper;
import com.sanyou.pojo.Product;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.service.ProductService;
import com.sanyou.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: asus
 * Date: 2021-12-14
 * Time: 13:36
 * Version:V1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(ProductVo queryVo, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<ProductVo> dataList = productMapper.query(queryVo);

        for (ProductVo vo : dataList) {
            if(vo.getProductState() == null || vo.getProductState() != 1){
                vo.setMaintainType(null);
                vo.setUpdateMaintainDate(null);
            }
            if(vo.getYjbLength() != null && vo.getYjbWidth() != null && vo.getYjbHeight() != null)
                vo.setYjbSize(vo.getYjbLength() + "*" + vo.getYjbWidth() + "*" + vo.getYjbHeight());
        }

        PageInfo<ProductVo> pageList = new PageInfo<>(dataList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(dataList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
}
