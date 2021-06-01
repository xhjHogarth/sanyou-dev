package com.sanyou.controller;

import com.sanyou.pojo.Factory;
import com.sanyou.service.FactoryService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 1:43
 * Version:V1.0
 */
@Api(value = "厂家相关的接口",tags = {"厂家相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;


    @ApiOperation(value = "新增厂家", notes = "新增厂家")
    @ApiImplicitParam(name="factoryName",value = "厂家名",required = true,
            dataType = "String", paramType = "query")
    @PostMapping("/addFactory")
    public JSONResult addFactory(String factoryName){
        if(StringUtils.isBlank(factoryName))
            return JSONResult.errorMsg("厂家名不能为空!");

        Factory factory = factoryService.findByName(factoryName);
        if(factory == null){
            factoryService.addFactory(factoryName);
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("厂家已存在!");
        }

    }

    @ApiOperation(value = "修改厂家信息", notes = "修改厂家信息")
    @PostMapping("/updateFactoryInfo")
    public JSONResult updateFactoryInfo(@RequestBody Factory factory){
        if(factory == null || StringUtils.isBlank(factory.getId()))
            return JSONResult.errorMsg("厂家ID为空!");

        Factory findFactory = factoryService.findById(factory.getId());
        if(findFactory != null){
            factoryService.updateFactoryInfo(factory);
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("厂家不存在!");
        }
    }


    @ApiImplicitParam(name="factoryName",value = "厂家名",dataType = "String", paramType = "query")
    @ApiOperation(value = "搜索厂家", notes = "搜索厂家")
    @GetMapping("/query")
    public JSONResult query(String factoryName){
        List<Factory> factoryList = factoryService.query(factoryName);

        return JSONResult.ok(factoryList);
    }
}
