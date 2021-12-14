package com.sanyou.controller;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.service.ProductService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

/**
 * User: asus
 * Date: 2021-12-14
 * Time: 13:35
 * Version:V1.0
 */
@Api(value = "阴极板相关的接口",tags = {"阴极板相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/query")
    public JSONResult query(String query,Integer page, Integer pageSize,Integer state,Integer maintainType,String factoryId){
        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        ProductVo queryVo = new ProductVo();
        queryVo.setQuery(query);
        queryVo.setProductState(state);
        queryVo.setMaintainType(maintainType);
        queryVo.setFactoryId(factoryId);

        PagedResult pagedResult = productService.query(queryVo,page,pageSize);

        return JSONResult.ok(pagedResult);
    }

    @PostMapping("/updateProduct")
    public JSONResult updateProduct(@RequestBody ProductVo productVo){
        if(productVo == null || productVo.getId() == null)
            return JSONResult.errorMsg("阴极板不存在!");

        if(StringUtils.isBlank(productVo.getProductCode()))
            return JSONResult.errorMsg("阴极板编码不能为空!");

        if(productVo.getProductValue() == null)
            return JSONResult.errorMsg("垂直度不能为空!");

        if(productVo.getProductState() == null)
            return JSONResult.errorMsg("阴极板状态不能为空!");

        Product product = new Product();
        product.setId(productVo.getId());
        product.setProductCode(productVo.getProductCode());
        product.setProductValue(productVo.getProductValue());
        if(productVo.getProductState()==1){
            product.setMaintainType(productVo.getMaintainType());
            product.setUserid(productVo.getUserid());
            product.setUpdateMaintainDate(new Date());
        }else if(productVo.getProductState() < 0 || productVo.getProductState() > 3){
            return JSONResult.errorMsg("阴极板状态异常!");
        }
        product.setProductState(productVo.getProductState());
        product.setUpdateStateDate(new Date());

        productService.updateProduct(product);

        return JSONResult.ok();
    }
}
