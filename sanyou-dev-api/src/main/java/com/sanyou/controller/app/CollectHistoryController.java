package com.sanyou.controller.app;

import com.sanyou.pojo.CollectHistory;
import com.sanyou.service.CollectHistoryService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-11-01
 * Time: 17:07
 * Version:V1.0
 */
@Api(value = "我的收藏的接口",tags = {"我的收藏的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/collectHistory")
public class CollectHistoryController {

    @Autowired
    private CollectHistoryService collectHistoryService;

    @GetMapping("/getCollectHistory")
    public JSONResult getCollectHistory(String userId){
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id不能为空!");

        List<CollectHistory> historyList = collectHistoryService.getCollectHistory(userId);
        return JSONResult.ok(historyList);
    }

    @PostMapping("/collect")
    public JSONResult collect(@RequestBody CollectHistory collectHistory){
        if(StringUtils.isBlank(collectHistory.getCollectCode()))
            return JSONResult.errorMsg("阴极板编码不能为空!");

        if(StringUtils.isBlank(collectHistory.getUserId()))
            return JSONResult.errorMsg("用户Id不能为空!");

        collectHistory.setCollectDate(new Date());

        collectHistoryService.collect(collectHistory);

        return JSONResult.ok();
    }

    @PostMapping("/unCollect")
    public JSONResult unCollect(@RequestBody CollectHistory collectHistory){
        if(StringUtils.isBlank(collectHistory.getCollectCode()))
            return JSONResult.errorMsg("阴极板编码不能为空!");

        if(StringUtils.isBlank(collectHistory.getUserId()))
            return JSONResult.errorMsg("用户Id不能为空!");

        collectHistoryService.unCollect(collectHistory);

        return JSONResult.ok();
    }
}
