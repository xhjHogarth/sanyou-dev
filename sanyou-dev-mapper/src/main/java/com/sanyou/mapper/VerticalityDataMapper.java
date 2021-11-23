package com.sanyou.mapper;

import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.VerticalityDataVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VerticalityDataMapper extends MyMapper<VerticalityData> {
    List<VerticalityDataVo> query(@Param("queryVo")VerticalityDataVo queryVo);
}