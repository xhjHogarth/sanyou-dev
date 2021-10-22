package com.sanyou.mapper;

import com.sanyou.pojo.ProjectData;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectDataMapper extends MyMapper<ProjectData> {


    List<ProjectData> getProjectList(@Param("userId") String userId);
}