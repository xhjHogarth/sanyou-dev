package com.sanyou.controller;

import com.sanyou.service.EquipmentRecordsService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

/**
 * User: asus
 * Date: 2021/6/6
 * Time: 1:28
 * Version:V1.0
 */
@Api(value = "设备参数修改记录相关的接口",tags = {"设备参数修改记录相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/equipmentRecords")
public class EquipmentRecordsController {

    @Autowired
    private EquipmentRecordsService equipmentRecordsService;


    @ApiImplicitParams({@ApiImplicitParam(name="query",value = "查询条件",required = false,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看设备参数修改记录列表", notes = "查看设备参数修改记录列表")
    @GetMapping("/query")
    public JSONResult query(String query, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult list = equipmentRecordsService.query(query, page, pageSize);

        return JSONResult.ok(list);
    }
}
