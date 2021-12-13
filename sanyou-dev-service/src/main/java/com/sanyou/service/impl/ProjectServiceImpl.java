package com.sanyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanyou.mapper.*;
import com.sanyou.pojo.*;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.pojo.vo.ProjectVo2;
import com.sanyou.service.ProjectService;
import com.sanyou.utils.PagedResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    private ProjectDataMapper projectDataMapper;

    @Autowired
    private ContractDataMapper contractDataMapper;

    @Autowired
    private VerticalityDataMapper verticalityDataMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    @Override
    public List<ProjectVo2> getProjectList(String userId) {
        List<ProjectVo2> projectList = new ArrayList<>();
        List<ProjectData> projectDataList = projectDataMapper.getProjectList(userId);

        if(projectDataList != null && projectDataList.size()>0){
            Factory factory = factoryMapper.selectByPrimaryKey(projectDataList.get(0).getFactoryId());

            int count = 1;
            for (ProjectData projectData : projectDataList) {
                ProjectVo2 projectVo = new ProjectVo2();
                BeanUtils.copyProperties(projectData,projectVo);

                projectVo.setProjectName(factory.getFactoryName() + "项目" + count);
                count++;

                projectList.add(projectVo);
            }
        }

        return projectList;
    }

    @Override
    public ProjectVo2 getContractDetail(String projectId) {

        ProjectVo2 projectVo = new ProjectVo2();

        Example example1 = new Example(ProjectData.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("projectId",projectId);
        ProjectData projectData = projectDataMapper.selectOneByExample(example1);
        BeanUtils.copyProperties(projectData,projectVo);


        Example example2 = new Example(ContractData.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("projectId",projectId);
        List<ContractData> contractDataList = contractDataMapper.selectByExample(example2);
        if(contractDataList == null || contractDataList.size() == 0){
            projectVo.setContractDataList(new ArrayList<>());
            return projectVo;
        }
        projectVo.setContractDataList(contractDataList);

        Example example3 = new Example(VerticalityData.class);
        Example.Criteria criteria3 = example3.createCriteria();
        criteria3.andEqualTo("projectId",projectId);
        List<VerticalityData> verticalityDataList = verticalityDataMapper.selectByExample(example3);

        if(verticalityDataList != null && verticalityDataList.size() > 0){

            projectVo.setTotalNum(verticalityDataList.size());

            //TODO 不同的state代表什么?
            projectVo.setUsingNum((int)verticalityDataList.stream().filter(t -> t.getState()==0).count());
            projectVo.setMaintainNum((int)verticalityDataList.stream().filter(t -> t.getState()==1).count());
            projectVo.setReserveNum((int)verticalityDataList.stream().filter(t -> t.getState()==2).count());
            projectVo.setDeprecatedNum((int)verticalityDataList.stream().filter(t -> t.getState()==3).count());
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
        List<ProjectVo> list = projectMapper.queryProjects(query);

        for (ProjectVo projectVo : list) {
            if(StringUtils.isNotBlank(projectVo.getFilename()))
                projectVo.setHasFile(1);
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
