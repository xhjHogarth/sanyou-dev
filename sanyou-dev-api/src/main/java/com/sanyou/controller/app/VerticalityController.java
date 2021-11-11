package com.sanyou.controller.app;

import com.sanyou.pojo.VerticalityData;
import com.sanyou.service.VerticalityService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User: asus
 * Date: 2021-11-11
 * Time: 1:36
 * Version:V1.0
 */
@Api(value = "阴极板相关的接口",tags = {"阴极板相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/verticality")
public class VerticalityController {

    @Autowired
    private VerticalityService verticalityService;

    @PostMapping("/updateState")
    public JSONResult updateState(@RequestBody VerticalityData verticalityData){
        if(verticalityData == null || StringUtils.isBlank(verticalityData.getVerticalityId())
        || verticalityData.getState()==null)
            return JSONResult.errorMsg("阴极板不存在!");

        verticalityService.updateState(verticalityData);
        return JSONResult.ok();
    }

    @GetMapping("/checkExist")
    public JSONResult checkExist(String code){
        if(StringUtils.isBlank(code))
            return JSONResult.errorMsg("阴极板不存在");

        VerticalityData verticalityData = verticalityService.query(code);
        if(verticalityData != null)
            return JSONResult.ok();
        else
            return JSONResult.errorMsg("阴极板不存在");
    }
}
