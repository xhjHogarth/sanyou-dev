package com.sanyou.controller;

import com.sanyou.pojo.SAdministrativeDivisions;
import com.sanyou.service.AdministrationService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/7
 * Time: 15:40
 * Version:V1.0
 */
@Api(value = "行政区划相关的接口",tags = {"行政区划相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/administration")
public class AdministrationController {


    @Autowired
    private AdministrationService administrationService;

    @ApiImplicitParams({@ApiImplicitParam(name="level",value = "行政级别",required = true,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pcode", value = "所属行政区划的编码", required = false,
                    dataType = "string", paramType = "query")})
    @ApiOperation(value = "查找行政区划数据", notes = "查找行政区划数据")
    @GetMapping("/query")
    public JSONResult query(String level,String pcode){

        if(StringUtils.isBlank(level))
            return JSONResult.errorMsg("行政级别为空!");

        List<SAdministrativeDivisions> list = administrationService.query(level,pcode);

        return JSONResult.ok(list);
    }
}
