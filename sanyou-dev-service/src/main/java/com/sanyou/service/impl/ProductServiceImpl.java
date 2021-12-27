package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.*;
import com.sanyou.pojo.*;
import com.sanyou.pojo.vo.ProductVo;
import com.sanyou.service.ProductService;
import com.sanyou.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @Autowired
    private IndustryDataMapper industryDataMapper;

    @Autowired
    private CollectHistoryMapper collectHistoryMapper;

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private VerticalityDataMapper verticalityDataMapper;

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Product query(String code) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productCode",code);
        Product product = productMapper.selectOneByExample(example);

        return product;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ProductVo getInfo(String scanCode, String userId, boolean flag) {
        ProductVo product = productMapper.selectByCode(scanCode);

        SearchHistory searchHistory = new SearchHistory();

        if(product != null){
            Example example2 = new Example(IndustryData.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("plateno",scanCode);
            List<IndustryData> industryDataList = industryDataMapper.selectByExample(example2);
            product.setIndustryDataList(industryDataList);

            searchHistory.setVerticality(product.getProductValue());

            //查询阴极板是否被收藏
            Example example3 = new Example(CollectHistory.class);
            Example.Criteria criteria3 = example3.createCriteria();
            criteria3.andEqualTo("userId",userId);
            criteria3.andEqualTo("collectCode",scanCode);
            List<CollectHistory> collectHistoryList = collectHistoryMapper.selectByExample(example3);
            if(collectHistoryList != null && collectHistoryList.size() > 0)
                product.setCollectStatus(1);
            else
                product.setCollectStatus(2);

            if(product.getDdbLength() != null && product.getDdbWidth() != null && product.getDdbHeight() != null)
                product.setDdbSize(product.getDdbLength() + "*" + product.getDdbWidth() + "*" + product.getDdbHeight());
            else
                product.setDdbSize("");

            if(product.getYjbLength() != null && product.getYjbWidth() != null && product.getYjbHeight() != null)
                product.setYjbSize(product.getYjbLength() + "*" + product.getYjbWidth() + "*" + product.getYjbHeight());
            else
                product.setYjbSize("");
        }

        //添加搜索记录
        searchHistory.setSearchCode(scanCode);
        searchHistory.setUserId(userId);
        searchHistory.setSearchDate(new Date());
        if(flag)
            searchHistoryMapper.insertSelective(searchHistory);

        return product;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateState(ProductVo productVo) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productCode",productVo.getProductCode());
        Product product = productMapper.selectOneByExample(example);

        if(product != null){
            if(productVo.getProductState() != null) {
                product.setProductState(productVo.getProductState());
                product.setUpdateStateDate(new Date());
                if(productVo.getProductState() == 1){
                    product.setMaintainType(productVo.getMaintainType());
                    product.setUpdateMaintainDate(new Date());
                    product.setUserid(productVo.getUserid());
                }else{
                    product.setMaintainType(null);
                    product.setUpdateMaintainDate(null);
                    product.setUserid(null);
                }
            }

            productMapper.updateProduct(product);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addProduct(Product product) {
        product.setCreatetime(new Date());
        productMapper.insertSelective(product);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int checkCount(String startProductCode, String endProductCode) {

        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("productCode", startProductCode, endProductCode);
        List<Product> productList = productMapper.selectByExample(example);

        return productList==null?0:productList.size();
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<VerticalityData> getList() {
        List<VerticalityData> list = verticalityDataMapper.selectAll();
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void copyData(List<Product> productList) {
        productMapper.insertList(productList);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Product> getAbsentList(String startProductCode, String endProductCode) {
        List<Product> absentList = new ArrayList<>();
        int length = startProductCode.length();

        List<Product> existList = productMapper.getExistList(startProductCode,endProductCode);
        Long startCode = Long.valueOf(startProductCode);
        Long endCode = Long.valueOf(endProductCode);
        if(existList == null || existList.size() == 0){
            for(long i = startCode;i<=endCode;i++){
                addProduct(absentList,i,length);
            }
        }else{
            for(long i = startCode;i<=endCode;i++){
                if(existList.size() == 0){
                    addProduct(absentList,i,length);
                }else{
                    Product exist = existList.get(0);
                    if(i == Long.valueOf(exist.getProductCode())){
                        existList.remove(0);
                    }else{
                        addProduct(absentList,i,length);
                    }
                }
            }
        }


        return absentList;
    }

    private void addProduct(List<Product> list,long code,int length){
        Product addProduct = new Product();
        int len = 0;
        long temp = code;
        while(temp/10 > 0){
            len++;
            temp = temp/10;
        }
        if(temp>0)
            len++;
        if(temp == length)
            addProduct.setProductCode(String.valueOf(code));
        else{
            String resultCode = String.valueOf(code);
            for(int i=0;i<length-len;i++){
                resultCode = "0" + resultCode;
            }
            addProduct.setProductCode(resultCode);
        }
        addProduct.setCreatetime(new Date());
        list.add(addProduct);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addProductList(List<Product> productList) {
        productMapper.insertList(productList);
    }
}
