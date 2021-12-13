package com.sanyou.service;

import com.sanyou.pojo.ProjectFile;

/**
 * User: asus
 * Date: 2021-12-12
 * Time: 16:18
 * Version:V1.0
 */
public interface ProjectFileService {

    void add(ProjectFile projectFile);

    void update(ProjectFile projectFile);

    ProjectFile findByPid(Integer pid);
}
