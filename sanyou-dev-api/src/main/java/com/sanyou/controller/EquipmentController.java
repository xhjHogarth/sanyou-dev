package com.sanyou.controller;

import com.sanyou.pojo.Equipment;
import com.sanyou.pojo.vo.EquipmentVo;
import com.sanyou.service.EquipmentService;
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
 * Date: 2021/6/5
 * Time: 17:37
 * Version:V1.0
 */
@Api(value = "设备相关的接口",tags = {"设备相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation(value = "新增设备", notes = "新增设备")
    @PostMapping("/addEquipment")
    public JSONResult addEquipment(@RequestBody Equipment equipment){

        if(equipment == null || StringUtils.isBlank(equipment.getEquipNo()))
            return JSONResult.errorMsg("设备编号不允许为空!");

        if(StringUtils.isBlank(equipment.getFactoryId()))
            return JSONResult.errorMsg("厂家名称不允许为空!");

        if(StringUtils.isBlank(equipment.getSubFactoryId()))
            return JSONResult.errorMsg("生产线不允许为空!");

        if(equipment.getEquipCycle() == null)
            return JSONResult.errorMsg("阴极周期不允许为空!");

        if(equipment.getEquipHealthLimit() == null)
            return JSONResult.errorMsg("健康临界点不允许为空!");

        if(equipment.getEquipSubhealthLimit() == null)
            return JSONResult.errorMsg("亚健康临界点不允许为空!");

        equipmentService.addEquipment(equipment);

        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name="page",value = "分页数",required = true,
            dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看设备列表", notes = "查看设备列表")
    @PostMapping("/query")
    public JSONResult query(@RequestBody EquipmentVo equipmentVo, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult pagedResult = equipmentService.query(equipmentVo,page,pageSize);

        return JSONResult.ok(pagedResult);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "设备id", required = true,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "mark", value = "启用/禁用标志", required = true,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "启用/禁用设备", notes = "启用/禁用设备")
    @PostMapping("/enableOrUnEnableEquip")
    public JSONResult enableOrUnEnableEquip(String id , int mark){
        if(StringUtils.isBlank(id))
            return JSONResult.errorMsg("设备id为空");

        equipmentService.enableOrUnEnableEquip(id, mark);

        return JSONResult.ok();
    }


    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "修改设备信息",notes = "修改设备信息")
    @PostMapping("/updateEquipInfo")
    public JSONResult updateEquipInfo(@RequestBody Equipment equipment,String userId){

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户id不能为空!");

        if(equipment == null || StringUtils.isBlank(equipment.getEquipNo()))
            return JSONResult.errorMsg("设备编号不允许为空!");

        if(StringUtils.isBlank(equipment.getFactoryId()))
            return JSONResult.errorMsg("厂家名称不允许为空!");

        if(StringUtils.isBlank(equipment.getSubFactoryId()))
            return JSONResult.errorMsg("生产线不允许为空!");

        if(equipment.getEquipCycle() == null)
            return JSONResult.errorMsg("阴极周期不允许为空!");

        if(equipment.getEquipHealthLimit() == null)
            return JSONResult.errorMsg("健康临界点不允许为空!");

        if(equipment.getEquipSubhealthLimit() == null)
            return JSONResult.errorMsg("亚健康临界点不允许为空!");


        equipmentService.updateEquipInfo(equipment,userId);

        return JSONResult.ok();
    }

    @ApiOperation(value = "查询所有设备,生成树结构返回", notes = "查询所有设备,生成树结构返回")
    @GetMapping("/getEquipmentTree")
    public JSONResult getEquipmentTree(){

        List<EquipmentVo> list = equipmentService.getEquipmentTree();

        return JSONResult.ok(list);
    }

    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "查询用户拥有的设备权限", notes = "查询用户拥有的设备权限")
    @GetMapping("/getUserEquip")
    public JSONResult getUserEquip(String userId){

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户id为空!");

        List<Equipment> equipmentList = equipmentService.getUserEquip(userId);

        return JSONResult.ok(equipmentList);
    }
}
