package com.sanyou.controller;

import com.sanyou.pojo.Factory;
import com.sanyou.service.FactoryService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

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
    @PostMapping("/addFactory")
    public JSONResult addFactory(@RequestBody Factory factory){
        if(factory == null || StringUtils.isBlank(factory.getFactoryName()))
            return JSONResult.errorMsg("厂家名不能为空!");

        Factory existFactory = factoryService.findByName(factory.getFactoryName());
        if(existFactory == null){
            factoryService.addFactory(factory);
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


    @ApiImplicitParams({@ApiImplicitParam(name="query",value = "查询条件",required = false,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看厂家列表", notes = "查看厂家列表")
    @GetMapping("/query")
    public JSONResult query(String query, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult factoryList = factoryService.query(query, page, pageSize);

        return JSONResult.ok(factoryList);
    }

    @ApiImplicitParam(name="parentId",value = "上级公司ID",required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "查询下级公司或部门", notes = "查询下级公司或部门")
    @GetMapping("/querySubFactory")
    public JSONResult querySubFactory(String parentId){

        if(StringUtils.isBlank(parentId))
            return JSONResult.errorMsg("上级厂家id为空!");

        List<Factory> list = factoryService.querySubFactory(parentId);

        return JSONResult.ok(list);
    }

    @ApiOperation(value = "查询所有厂家", notes = "查询所有厂家")
    @GetMapping("/getAll")
    public JSONResult getAll(HttpServletRequest request){

        String remoteAddr = request.getRemoteAddr();

        List<Factory> factoryList = factoryService.getAll();

        return JSONResult.ok(factoryList);
    }
}
