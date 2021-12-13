package com.sanyou.service.impl;

import com.sanyou.mapper.ProjectFileMapper;
import com.sanyou.pojo.ProjectFile;
import com.sanyou.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * User: asus
 * Date: 2021-12-12
 * Time: 16:18
 * Version:V1.0
 */
@Service
public class ProjectFileServiceImpl implements ProjectFileService {

    @Autowired
    private ProjectFileMapper projectFileMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(ProjectFile projectFile) {
        projectFileMapper.insert(projectFile);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(ProjectFile projectFile) {
        Example example = new Example(ProjectFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectFile.getProjectId());

        projectFileMapper.deleteByExample(example);

        projectFileMapper.insert(projectFile);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ProjectFile findByPid(Integer pid) {
        Example example = new Example(ProjectFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",pid);
        ProjectFile projectFile = projectFileMapper.selectOneByExample(example);
        return projectFile;
    }
}
