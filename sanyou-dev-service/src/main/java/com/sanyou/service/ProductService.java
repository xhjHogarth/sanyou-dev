package com.sanyou.service;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021-12-14
 * Time: 13:36
 * Version:V1.0
 */
public interface ProductService {
    PagedResult query(ProductVo queryVo, Integer page, Integer pageSize);

    void updateProduct(Product product);

    /**
     * 根据阴极板编码获取阴极板信息
     * @param scanCode 阴极板编码
     * @param userId 用户id
     * @param flag 是否添加搜索记录
     * @return
     */
    ProductVo getInfo(String scanCode, String userId, boolean flag);

    Product query(String code);

    void updateState(ProductVo productVo);

    void addProduct(Product product);

    void deleteProduct(Integer id);

    int checkCount(String startProductCode, String endProductCode);

    List<VerticalityData> getList();

    void copyData(List<Product> productList);

    List<Product> getAbsentList(String startProductCode, String endProductCode);

    void addProductList(List<Product> productList);
}
