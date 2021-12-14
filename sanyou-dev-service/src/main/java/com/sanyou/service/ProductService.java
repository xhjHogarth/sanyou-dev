package com.sanyou.service;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.utils.PagedResult;

/**
 * User: asus
 * Date: 2021-12-14
 * Time: 13:36
 * Version:V1.0
 */
public interface ProductService {
    PagedResult query(ProductVo queryVo, Integer page, Integer pageSize);

    void updateProduct(Product product);
}
