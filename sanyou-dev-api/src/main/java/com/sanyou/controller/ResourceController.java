package com.sanyou.controller;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.vo.ResourceVo;
import com.sanyou.service.ResourceService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 14:22
 * Version:V1.0
 */
@Api(value = "资源相关的接口",tags = {"资源相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "添加资源", notes = "添加资源")
    @ApiImplicitParam(name="parentCode",value = "父级编码",required = false,
            dataType = "string", paramType = "query")
    @PostMapping("/addResource")
    public JSONResult addResource(@RequestBody Resource resource,String parentCode){
        if(resource == null || StringUtils.isBlank(resource.getResCode()) || StringUtils.isBlank(resource.getTitle()))
            return JSONResult.errorMsg("资源编码或名称不能为空!");

        Resource resource1 = resourceService.findByName(resource.getResCode());
        if(resource1 == null){
            if(StringUtils.isNotBlank(parentCode)){
                Resource resource2 = resourceService.findByName(parentCode);
                if(resource2 != null){
                    resource.setParentId(resource2.getId());
                }else{
                    return JSONResult.errorMsg("父编码错误,请正确填写父编码!");
                }
            }
            resourceService.addResource(resource);
        }else{
            return JSONResult.errorMsg("资源编码已存在!");
        }


        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name="query",value = "查询条件",required = false,
                    dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看资源列表", notes = "查看资源列表")
    @GetMapping("/query")
    public JSONResult query(String query, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult result = resourceService.query(query,page,pageSize);

        return JSONResult.ok(result);
    }

    @ApiOperation(value = "修改资源信息", notes = "修改资源信息")
    @PostMapping("/updateResources")
    public JSONResult updateResources(@RequestBody List<Resource> list){

        if(list == null || list.size() == 0)
            return JSONResult.ok();

        resourceService.updateResource(list);

        return JSONResult.ok();
    }

    @ApiOperation(value = "删除资源", notes = "删除资源")
    @PostMapping("/deleteResources")
    public JSONResult deleteResources(@RequestBody List<Resource> list){

        if(list == null || list.size() == 0)
            return JSONResult.ok();

        resourceService.updateResource(list);

        return JSONResult.ok();
    }

    @ApiOperation(value = "获取资源列表", notes = "获取资源列表")
    @GetMapping("/getAll")
    public JSONResult getAll(){

        List<ResourceVo> list = resourceService.getAll();

        return JSONResult.ok(list);
    }
}
