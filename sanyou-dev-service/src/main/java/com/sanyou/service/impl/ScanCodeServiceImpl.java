package com.sanyou.service.impl;

import com.sanyou.mapper.*;
import com.sanyou.pojo.*;
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

    @Autowired
    private CollectHistoryMapper collectHistoryMapper;

    @Autowired
    private ProjectDataMapper projectDataMapper;


    @Override
    public VerticalityDataVo getInfo(String scanCode,String userId,boolean flag) {

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

            //查询阴极板是否被收藏
            Example example3 = new Example(CollectHistory.class);
            Example.Criteria criteria3 = example3.createCriteria();
            criteria3.andEqualTo("userId",userId);
            criteria3.andEqualTo("collectCode",scanCode);
            List<CollectHistory> collectHistoryList = collectHistoryMapper.selectByExample(example3);
            if(collectHistoryList != null && collectHistoryList.size() > 0)
                verticalityDataVo.setCollectStatus(1);
            else
                verticalityDataVo.setCollectStatus(2);

            //根据projectId获取阴极板尺寸和导电棒尺寸
            String projectId = verticalityDataVo.getProjectId();
            Example example4 = new Example(ProjectData.class);
            Example.Criteria criteria4 = example4.createCriteria();
            criteria4.andEqualTo("projectId",projectId);
            ProjectData projectData = projectDataMapper.selectOneByExample(example4);
            String ddbLength = projectData.getDdbLength()==null?"":projectData.getDdbLength()+"";
            String ddbWidth = projectData.getDdbWidth()==null?"":projectData.getDdbWidth()+"";
            String ddbHeight = projectData.getDdbHeight()==null?"":projectData.getDdbHeight()+"";
            String yjbLength = projectData.getYjbLength()==null?"":projectData.getYjbLength()+"";
            String yjbWidth = projectData.getYjbWidth()==null?"":projectData.getYjbWidth()+"";
            String yjbHeight = projectData.getYjbHeight()==null?"":projectData.getYjbHeight()+"";

            verticalityDataVo.setDdbSize(ddbLength + "*" + ddbWidth + "*" + ddbHeight);
            verticalityDataVo.setYjbSize(yjbLength + "*" + yjbWidth + "*" + yjbHeight);
        }

        //添加搜索记录
        searchHistory.setSearchCode(scanCode);
        searchHistory.setUserId(userId);
        searchHistory.setSearchDate(new Date());
        if(flag)
            searchHistoryMapper.insertSelective(searchHistory);

        return verticalityDataVo;
    }
}
