package com.sanyou.controller.app;

import com.sanyou.pojo.SearchHistory;
import com.sanyou.service.SearchHistoryService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-30
 * Time: 14:12
 * Version:V1.0
 */
@Api(value = "扫码搜索历史记录的接口",tags = {"扫码搜索历史记录的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/searchHistory")
public class SearchHistoryController {

    @Autowired
    private SearchHistoryService searchHistoryService;


    @GetMapping("/getSearchHistory")
    public JSONResult getSearchHistory(String userId){
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id不能为空!");

        List<SearchHistory> historyList = searchHistoryService.getSearchHistory(userId);
        return JSONResult.ok(historyList);
    }

}
