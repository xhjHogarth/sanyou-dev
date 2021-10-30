package com.sanyou.service.impl;

import com.sanyou.mapper.IndustryDataMapper;
import com.sanyou.mapper.SearchHistoryMapper;
import com.sanyou.mapper.VerticalityDataMapper;
import com.sanyou.pojo.IndustryData;
import com.sanyou.pojo.SearchHistory;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.service.ScanCodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-10-28
 * Time: 22:11
 * Version:V1.0
 */
@Service
public class ScanCodeServiceImpl implements ScanCodeService {


    @Autowired
    private IndustryDataMapper industryDataMapper;

    @Autowired
    private VerticalityDataMapper verticalityDataMapper;

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;


    @Override
    public VerticalityDataVo getInfo(String scanCode,String userId) {

        VerticalityDataVo verticalityDataVo = new VerticalityDataVo();

        Example example = new Example(VerticalityData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("verticalityId",scanCode);
        List<VerticalityData> verticalityDataList = verticalityDataMapper.selectByExample(example);

        SearchHistory searchHistory = new SearchHistory();

        if(verticalityDataList != null && verticalityDataList.size() > 0){
            VerticalityData verticalityData = verticalityDataList.get(0);
            BeanUtils.copyProperties(verticalityData,verticalityDataVo);

            Example example2 = new Example(IndustryData.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("plateno",scanCode);
            List<IndustryData> industryDataList = industryDataMapper.selectByExample(example2);

            verticalityDataVo.setIndustryDataList(industryDataList);

            searchHistory.setVerticality(verticalityData.getVerticality());
        }

        //添加搜索记录
        searchHistory.setSearchCode(scanCode);
        searchHistory.setUserId(userId);
        searchHistory.setSearchDate(new Date());
        searchHistoryMapper.insertSelective(searchHistory);

        return verticalityDataVo;
    }
}
