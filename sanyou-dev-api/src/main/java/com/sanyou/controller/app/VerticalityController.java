package com.sanyou.controller.app;

import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.service.VerticalityService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

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
        if(StringUtils.isBlank(verticalityData.getUserid()))
            return JSONResult.errorMsg("用户Id为空!");

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

    @GetMapping("/query")
    public JSONResult query(String query,Integer page, Integer pageSize,Integer state,Integer maintainType){
        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        VerticalityDataVo queryVo = new VerticalityDataVo();
        queryVo.setQuery(query);
        queryVo.setState(state);
        queryVo.setMaintainType(maintainType);

        PagedResult pagedResult = verticalityService.query(queryVo,page,pageSize);

        return JSONResult.ok(pagedResult);
    }
}
