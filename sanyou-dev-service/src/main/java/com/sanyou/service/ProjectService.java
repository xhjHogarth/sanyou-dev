package com.sanyou.service;

import com.sanyou.pojo.Project;
import com.sanyou.pojo.vo.ProjectVo2;
import com.sanyou.utils.PagedResult;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-22
 * Time: 23:51
 * Version:V1.0
 */
public interface ProjectService {

    /**
     * 根据用户id查询项目列表
     * @param userId
     * @return
     */
    List<ProjectVo2> getProjectList(String userId);

    /**
     * 根据项目Id，合同Id获取合同详情信息
     * @param projectId 项目Id
     * @return
     */
    ProjectVo2 getContractDetail(String projectId);

    /**
     * 新增项目
     * @param project
     * @return
     */
    int addProject(Project project);

    /**
     * 判断项目名或编码是否存在
     * @param project 项目
     * @return
     */
    boolean checkNameAndCode(Project project);

    /**
     * 修改项目信息
     * @param project
     */
    void updateProject(Project project);

    /**
     * 删除项目
     * @param pid
     */
    void deleteProject(Integer pid);

    PagedResult query(String query,Integer page, Integer pageSize);

    List<Project> getAll();

}
