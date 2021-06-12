package com.sanyou.controller;

import com.sanyou.pojo.UserLog;
import com.sanyou.pojo.vo.UserLogVo;
import com.sanyou.service.UserLogService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

/**
 * User: asus
 * Date: 2021/6/9
 * Time: 21:33
 * Version:V1.0
 */
@Api(value = "操作日志相关的接口",tags = {"操作日志相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/userLog")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;


    @ApiImplicitParams({@ApiImplicitParam(name="page",value = "分页数",required = true,
            dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看操作日志列表", notes = "查看操作日志列表")
    @PostMapping("/query")
    public JSONResult query(@RequestBody UserLogVo userLogVo, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult pagedResult = userLogService.query(userLogVo,page,pageSize);

        return JSONResult.ok(pagedResult);
    }

    @ApiOperation(value = "删除操作日志", notes = "删除操作日志")
    @PostMapping("/deleteUserLog")
    public JSONResult deleteUserLog(@RequestBody List<UserLog> list){

        if(list == null || list.size() == 0)
            return JSONResult.ok();

        userLogService.deleteUserLog(list);

        return JSONResult.ok();
    }


    @ApiOperation(value = "下载操作日志", notes = "下载操作日志")
    @GetMapping("/download")
    public void download(Date createtime, HttpServletResponse response) throws IOException {
        UserLogVo userLogVo = new UserLogVo();
        userLogVo.setCreatetime(createtime);
        List<UserLogVo> list = userLogService.queryForDownload(userLogVo);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("日志表");

        long timeMillis = System.currentTimeMillis();
        String fileName = timeMillis + ".xls";
        int  rowNum = 1;
        String[] headers = {"用户名","模块","动作","IP","时间","接口地址"};
        HSSFRow row = sheet.createRow(0);

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        for (UserLogVo vo : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(vo.getUsername());
            row1.createCell(1).setCellValue(vo.getModule());
            row1.createCell(2).setCellValue(vo.getAction());
            row1.createCell(3).setCellValue(vo.getIp());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String format1 = format.format(vo.getCreatetime());
            row1.createCell(4).setCellValue(format1);
            row1.createCell(5).setCellValue(vo.getUrl());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @ApiOperation(value = "检查日志下载权限", notes = "检查日志下载权限")
    @GetMapping("/downloadUserLog")
    private JSONResult downloadUserLog(){
        return JSONResult.ok();
    }
}
