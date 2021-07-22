package com.sanyou.controller;

import com.sanyou.pojo.IndustryData;
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
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
                List<IndustryDataVo> result = industryDataService.getNormalLineChart2(industryDataVo);

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

    @ApiOperation(value = "下载数据", notes = "下载数据")
    @GetMapping("/download")
    public void download(Date startTime,Date endTime,String equipNo, HttpServletResponse response) throws IOException {
        IndustryDataVo industryDataVo = new IndustryDataVo();
        industryDataVo.setStartTime(startTime);
        industryDataVo.setEndTime(endTime);
        industryDataVo.setLineno(equipNo);

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

        List<IndustryData> list = industryDataService.queryDownloadData(industryDataVo);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        sheet.setColumnWidth(0,30*256);
        sheet.setColumnWidth(3,50*256);
        sheet.setColumnWidth(4,50*256);
        sheet.setColumnWidth(5,50*256);
        sheet.setColumnWidth(6,50*256);
        sheet.setColumnWidth(7,50*256);
        sheet.setColumnWidth(8,50*256);
        sheet.setColumnWidth(9,50*256);
        sheet.setColumnWidth(10,50*256);
        sheet.setColumnWidth(11,50*256);
        sheet.setColumnWidth(12,50*256);
        sheet.setColumnWidth(13,50*256);
        sheet.setColumnWidth(14,50*256);
        sheet.setColumnWidth(15,50*256);
        sheet.setColumnWidth(16,50*256);
        sheet.setColumnWidth(17,50*256);
        sheet.setColumnWidth(18,50*256);
        sheet.setColumnWidth(19,50*256);
        sheet.setColumnWidth(20,50*256);
        sheet.setColumnWidth(21,50*256);
        sheet.setColumnWidth(22,50*256);
        sheet.setColumnWidth(23,50*256);
        sheet.setColumnWidth(24,50*256);
        sheet.setColumnWidth(25,50*256);
        sheet.setColumnWidth(26,50*256);
        sheet.setColumnWidth(27,50*256);
        sheet.setColumnWidth(28,50*256);


        long timeMillis = System.currentTimeMillis();
        String fileName = timeMillis + ".xls";
        int  rowNum = 1;
        String[] headers = {"检测时间","产品编号","设备编号","MAX1","MAX2","MAX3","MAX4","MAX5","MAX6","MAX7","MAX8","MAX9",
                            "MAX10","MAX11","MAX12","MAX13","MAX14","MAX15","MAX16","MAX17","MAX18","MAX19","MAX20","MAX21",
                            "MAX22","MAX23","MAX24","MAX25","MAX26","MAX","MAX NO"};
        HSSFRow row = sheet.createRow(0);

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (IndustryData industryData : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(format.format(industryData.getDatatime()));
            row1.createCell(1).setCellValue(industryData.getPlateno());
            row1.createCell(2).setCellValue(industryData.getLineno());
            row1.createCell(3).setCellValue(industryData.getMax1());
            row1.createCell(4).setCellValue(industryData.getMax2());
            row1.createCell(5).setCellValue(industryData.getMax3());
            row1.createCell(6).setCellValue(industryData.getMax4());
            row1.createCell(7).setCellValue(industryData.getMax5());
            row1.createCell(8).setCellValue(industryData.getMax6());
            row1.createCell(9).setCellValue(industryData.getMax7());
            row1.createCell(10).setCellValue(industryData.getMax8());
            row1.createCell(11).setCellValue(industryData.getMax9());
            row1.createCell(12).setCellValue(industryData.getMax10());
            row1.createCell(13).setCellValue(industryData.getMax11());
            row1.createCell(14).setCellValue(industryData.getMax12());
            row1.createCell(15).setCellValue(industryData.getMax13());
            row1.createCell(16).setCellValue(industryData.getMax14());
            row1.createCell(17).setCellValue(industryData.getMax15());
            row1.createCell(18).setCellValue(industryData.getMax16());
            row1.createCell(19).setCellValue(industryData.getMax17());
            row1.createCell(20).setCellValue(industryData.getMax18());
            row1.createCell(21).setCellValue(industryData.getMax19());
            row1.createCell(22).setCellValue(industryData.getMax20());
            row1.createCell(23).setCellValue(industryData.getMax21());
            row1.createCell(24).setCellValue(industryData.getMax22());
            row1.createCell(25).setCellValue(industryData.getMax23());
            row1.createCell(26).setCellValue(industryData.getMax24());
            row1.createCell(27).setCellValue(industryData.getMax25());
            row1.createCell(28).setCellValue(industryData.getMax26());
            row1.createCell(29).setCellValue(industryData.getMax());
            row1.createCell(30).setCellValue(industryData.getMaxno());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
