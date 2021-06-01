package com.sanyou.mapper;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.vo.ResourceVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends MyMapper<Resource> {

    List<ResourceVo> queryResources(@Param("query") String query);
}