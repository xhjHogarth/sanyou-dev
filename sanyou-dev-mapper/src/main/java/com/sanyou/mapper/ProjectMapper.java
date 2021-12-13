package com.sanyou.mapper;

import com.sanyou.pojo.Project;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper extends MyMapper<Project> {
    List<ProjectVo> queryProjects(@Param("query") String query);

    void insertOne(Project project);

    int checkNameAndCode(Project project);
}