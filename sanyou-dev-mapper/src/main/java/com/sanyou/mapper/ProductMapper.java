package com.sanyou.mapper;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends MyMapper<Product> {
    List<ProductVo> getOrderProduct(Integer oid);

    int checkCodeRange(String startProductCode, String endProductCode);

    List<ProductVo> query(ProductVo queryVo);

    void updateProduct(Product product);

    List<Product> getProjectProducts(@Param("pid") Integer projectId);

    ProductVo selectByCode(@Param("scanCode") String scanCode);
}