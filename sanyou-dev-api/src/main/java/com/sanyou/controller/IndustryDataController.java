package com.sanyou.controller;

import com.sanyou.pojo.vo.IndustryDataVo;
import com.sanyou.service.IndustryDataService;
import com.sanyou.service.UserEquipmentService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/14
 * Time: 15:00
 * Version:V1.0
 */
@Api(value = "检测数据相关的接口",tags = {"检测数据相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/industryData")
public class IndustryDataController {

    @Autowired
    private IndustryDataService industryDataService;

    @Autowired
    private UserEquipmentService userEquipmentService;


    @ApiOperation(value = "获取饼图数据", notes = "获取饼图数据")
    @PostMapping("/getPieChart")
    public JSONResult getPieChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getPieChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }

    }

    @ApiOperation(value = "获取柱状图数据", notes = "获取柱状图数据")
    @PostMapping("/getBarChart")
    public JSONResult getBarChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getBarChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }

    }


    @ApiOperation(value = "获取折线图数据", notes = "获取折线图数据")
    @PostMapping("/getLineChart")
    public JSONResult getLineChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getLineChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }

    }

    @ApiImplicitParams({@ApiImplicitParam(name="page",value = "分页数",required = true,
            dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "分页获取数据", notes = "分页获取数据")
    @PostMapping("/query")
    public JSONResult query(@RequestBody IndustryDataVo industryDataVo, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = 20;

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                PagedResult result = industryDataService.query(industryDataVo,page,pageSize);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }
    }

    @ApiOperation(value = "获取周期折线图数据", notes = "获取周期折线图数据")
    @PostMapping("/getCycleLineChart")
    public JSONResult getCycleLineChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getCycleLineChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }
    }

    @ApiOperation(value = "获取正太折线图数据", notes = "获取正太折线图数据")
    @PostMapping("/getNormalLineChart")
    public JSONResult getNormalLineChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getNormalLineChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }
    }

    @ApiOperation(value = "获取正太柱状图数据", notes = "获取正太柱状图数据")
    @PostMapping("/getNormalBarChart")
    public JSONResult getNormalBarChart(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号不能为空!");

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");
        else{
            boolean haveAuth = userEquipmentService.checkAuth(industryDataVo.getLineno(),industryDataVo.getUserId());
            if(haveAuth){
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(industryDataVo.getStartTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                industryDataVo.setStartTime(calendar.getTime());
                calendar.setTime(industryDataVo.getEndTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DATE,1);
                industryDataVo.setEndTime(calendar.getTime());
                List<IndustryDataVo> result = industryDataService.getNormalBarChart(industryDataVo);

                return JSONResult.ok(result);
            }else {
                return JSONResult.build(503,"没有设备权限",null);
            }
        }
    }


    @ApiImplicitParams({@ApiImplicitParam(name="page",value = "分页数",required = true,
            dataType = "int", paramType = "query")})
    @ApiOperation(value = "分页获取数据", notes = "分页获取数据")
    @PostMapping("/queryData")
    public JSONResult queryData(@RequestBody IndustryDataVo industryDataVo, Integer page){

        if(page == null)
            page = 1;

        if(StringUtils.isBlank(industryDataVo.getUserId()))
            return JSONResult.errorMsg("用户id不能为空!");

        if("plateno".equals(industryDataVo.getQueryLabel()) && StringUtils.isNotBlank(industryDataVo.getQuery()))
            industryDataVo.setPlateno(industryDataVo.getQuery());
        if("lineno".equals(industryDataVo.getQueryLabel()) && StringUtils.isNotBlank(industryDataVo.getQuery()))
            industryDataVo.setLineno(industryDataVo.getQuery());

        IndustryDataVo vo = industryDataService.queryData(industryDataVo,page);

        return JSONResult.ok(vo);
    }

    @ApiOperation(value = "删除检测数据", notes = "删除检测数据")
    @PostMapping("/deleteData")
    public JSONResult deleteData(@RequestBody IndustryDataVo industryDataVo){

        if(industryDataVo == null || StringUtils.isBlank(industryDataVo.getPlateno()))
            return JSONResult.errorMsg("产品编号为空!");

        if(StringUtils.isBlank(industryDataVo.getLineno()))
            return JSONResult.errorMsg("设备编号为空!");

        if(industryDataVo.getDatatime() == null)
            return JSONResult.errorMsg("时间为空!");

        industryDataService.deleteData(industryDataVo);

        return JSONResult.ok();
    }
}
