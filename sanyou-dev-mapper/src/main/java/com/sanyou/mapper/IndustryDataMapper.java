package com.sanyou.mapper;

import com.sanyou.pojo.IndustryData;
import com.sanyou.pojo.vo.IndustryDataVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndustryDataMapper extends MyMapper<IndustryData> {


    List<IndustryDataVo> getPieChart(@Param("vo") IndustryDataVo industryDataVo);

    List<IndustryData> query(@Param("vo") IndustryDataVo industryDataVo);

    List<IndustryData> queryData(@Param("vo")IndustryDataVo industryDataVo);

    int countData(@Param("vo")IndustryDataVo industryDataVo);

}