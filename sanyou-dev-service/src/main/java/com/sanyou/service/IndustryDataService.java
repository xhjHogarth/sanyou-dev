package com.sanyou.service;

import com.sanyou.pojo.IndustryData;
import com.sanyou.pojo.vo.IndustryDataVo;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021/6/14
 * Time: 15:02
 * Version:V1.0
 */
public interface IndustryDataService {

    /**
     * 获取饼图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getPieChart(IndustryDataVo industryDataVo);

    /**
     * 获取柱状图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getBarChart(IndustryDataVo industryDataVo);

    /**
     * 获取折线图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getLineChart(IndustryDataVo industryDataVo);

    /**
     * 分页获取数据
     * @param industryDataVo
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult query(IndustryDataVo industryDataVo,Integer page, Integer pageSize);

    /**
     * 获取周期折线图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getCycleLineChart(IndustryDataVo industryDataVo);

    /**
     * 获取正太折线图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getNormalLineChart(IndustryDataVo industryDataVo);

    /**
     * 获取正太柱状图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getNormalBarChart(IndustryDataVo industryDataVo);

    /**
     * 分页获取数据
     * @param industryDataVo
     * @param page
     * @return
     */
    IndustryDataVo queryData(IndustryDataVo industryDataVo, Integer page);

    /**
     * 删除检测数据
     * @param industryDataVo
     */
    void deleteData(IndustryDataVo industryDataVo);

    /**
     * 下载数据
     * @param industryDataVo
     * @return
     */
    List<IndustryData> queryDownloadData(IndustryDataVo industryDataVo);

    /**
     * 获取正太折线图数据
     * @param industryDataVo
     * @return
     */
    List<IndustryDataVo> getNormalLineChart2(IndustryDataVo industryDataVo);
}
