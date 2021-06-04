package com.sanyou.mapper;

import com.sanyou.pojo.Factory;
import com.sanyou.pojo.vo.FactoryVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FactoryMapper extends MyMapper<Factory> {
    List<FactoryVo> query(@Param("factoryName") String factoryName);
}