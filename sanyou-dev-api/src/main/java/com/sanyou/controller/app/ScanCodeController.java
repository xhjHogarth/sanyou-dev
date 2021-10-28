package com.sanyou.controller.app;

import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.service.ScanCodeService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: asus
 * Date: 2021-10-28
 * Time: 22:09
 * Version:V1.0
 */
@Api(value = "扫码相关的接口",tags = {"扫码相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/scancode")
public class ScanCodeController {

    @Autowired
    private ScanCodeService scanCodeService;


    @GetMapping("/getInfo")
    public JSONResult getInfo(String scanCode){

        if(StringUtils.isBlank(scanCode))
            return JSONResult.errorMsg("阴极板编码为空!");

        VerticalityDataVo dataVo = scanCodeService.getInfo(scanCode);

        return JSONResult.ok(dataVo);
    }
}
