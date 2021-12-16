package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.*;
import com.sanyou.pojo.Product;
import com.sanyou.pojo.Project;
import com.sanyou.pojo.ProjectOrder;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.service.ProjectService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-10-22
 * Time: 23:51
 * Version:V1.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ProjectVo> getProjectList(String userId) {
        List<ProjectVo> projectList = projectMapper.queryProjects("",userId);

        return projectList;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ProjectVo getProject(Integer projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if(project == null)
            return null;
        ProjectVo projectVo = new ProjectVo();
        BeanUtils.copyProperties(project,projectVo);

        List<Product> productList = productMapper.getProjectProducts(projectId);
        if(productList != null && productList.size() > 0) {
            projectVo.setTotalNum(productList.size());
            projectVo.setUsingNum((int) productList.stream().filter(t -> t.getProductState() == 0).count());
            projectVo.setMaintainNum((int) productList.stream().filter(t -> t.getProductState() == 1).count());
            projectVo.setReserveNum((int) productList.stream().filter(t -> t.getProductState() == 2).count());
            projectVo.setDeprecatedNum((int) productList.stream().filter(t -> t.getProductState() == 3).count());
        }
        return projectVo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addProject(Project project) {
        project.setCreatetime(new Date());

        projectMapper.insertOne(project);
        return project.getId();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean checkNameAndCode(Project project) {

        int count = projectMapper.checkNameAndCode(project);

        return count>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateProject(Project project) {
        project.setUpdatetime(new Date());
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteProject(Integer pid) {
        Project project = new Project();
        project.setId(pid);
        project.setDeleteMark((byte) 1);

        projectMapper.updateByPrimaryKeySelective(project);

        Example example = new Example(ProjectOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",pid);
        projectOrderMapper.deleteByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult query(String query,Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<ProjectVo> list = projectMapper.queryProjects(query,null);

        for (ProjectVo projectVo : list) {
            if(StringUtils.isNotBlank(projectVo.getFilename()))
                projectVo.setHasFile(1);

            if(projectVo.getDdbLength() != null && projectVo.getDdbWidth() != null && projectVo.getDdbHeight() != null)
                projectVo.setDdbSize(projectVo.getDdbLength() + "*" + projectVo.getDdbWidth() + "*" + projectVo.getDdbHeight());

            if(projectVo.getYjbLength() != null && projectVo.getYjbWidth() != null && projectVo.getYjbHeight() != null)
                projectVo.setYjbSize(projectVo.getYjbLength() + "*" + projectVo.getYjbWidth() + "*" + projectVo.getYjbHeight());
        }

        PageInfo<ProjectVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Override
    public List<Project> getAll() {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteMark",0);
        List<Project> projectList = projectMapper.selectByExample(example);
        return projectList;
    }
}
