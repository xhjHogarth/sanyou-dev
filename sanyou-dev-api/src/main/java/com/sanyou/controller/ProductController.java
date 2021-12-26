package com.sanyou.controller;

import com.sanyou.pojo.Product;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.service.ProductService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //这是一个测试提交

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

    @PostMapping("/addProduct")
    public JSONResult addProduct(@RequestBody Product product){
        if(product == null || StringUtils.isBlank(product.getProductCode()))
            return JSONResult.errorMsg("阴极板编码不能为空!");
        if(product.getProductValue() == null)
            return JSONResult.errorMsg("垂直度不能为空!");

        productService.addProduct(product);
        return JSONResult.ok();
    }

    @PostMapping("/deleteProduct")
    public JSONResult deleteProduct(@RequestBody Product product){
        if(product == null || product.getId() == null)
            return JSONResult.errorMsg("阴极板不存在!");

        productService.deleteProduct(product.getId());
        return JSONResult.ok();
    }

    @PostMapping("/checkProductCode")
    public JSONResult checkProductCode(@RequestBody ProductVo productVo){
        if(productVo == null)
            return JSONResult.errorMsg("阴极板不存在!");

        if(StringUtils.isBlank(productVo.getStartProductCode()))
            return JSONResult.errorMsg("阴极板起始编码不能为空!");

        if(StringUtils.isBlank(productVo.getEndProductCode()))
            return JSONResult.errorMsg("阴极板截止编码不能为空!");

        if(Long.valueOf(productVo.getStartProductCode()) > Long.valueOf(productVo.getEndProductCode()))
            return JSONResult.errorMsg("起始编码不能大于截止编码!");

        int count = productService.checkCount(productVo.getStartProductCode(),productVo.getEndProductCode());
        if(count==0)
            return JSONResult.errorMsg("当前编码区间不存在阴极板!");

        int rcount = (int)(Long.valueOf(productVo.getEndProductCode())-Long.valueOf(productVo.getStartProductCode()))+1;
        if(rcount == count)
            return JSONResult.ok();

        return JSONResult.errorMsg("阴极板编码缺失!");
    }

    /**
     * 将verticality_data表的数据导入到product表
     * @return
     */
    @GetMapping("/copyData")
    public JSONResult copyData(){

        List<VerticalityData> dataList = productService.getList();
        List<Product> productList = new ArrayList<>();
        for (VerticalityData verticalityData : dataList) {
            Product product = new Product();
            product.setProductCode(verticalityData.getVerticalityId());
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(verticalityData.getVerticality()));
            product.setProductValue(bigDecimal.doubleValue());
            product.setProductState(0);
            product.setCreatetime(verticalityData.getCreatetime());

            productList.add(product);
        }
        productService.copyData(productList);

        return JSONResult.ok();
    }
}
