package com.sanyou.service.impl;

import com.sanyou.mapper.ContractDataMapper;
import com.sanyou.mapper.FactoryMapper;
import com.sanyou.mapper.ProjectDataMapper;
import com.sanyou.mapper.VerticalityDataMapper;
import com.sanyou.pojo.ContractData;
import com.sanyou.pojo.Factory;
import com.sanyou.pojo.ProjectData;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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

    @Override
    public List<ProjectVo> getProjectList(String userId) {
        List<ProjectVo> projectList = new ArrayList<>();
        List<ProjectData> projectDataList = projectDataMapper.getProjectList(userId);

        if(projectDataList != null && projectDataList.size()>0){
            Factory factory = factoryMapper.selectByPrimaryKey(projectDataList.get(0).getFactoryId());

            int count = 1;
            for (ProjectData projectData : projectDataList) {
                ProjectVo projectVo = new ProjectVo();
                BeanUtils.copyProperties(projectData,projectVo);

                projectVo.setProjectName(factory.getFactoryName() + "项目" + count);
                count++;

                projectList.add(projectVo);
            }
        }

        return projectList;
    }

    @Override
    public ProjectVo getContractDetail(String projectId) {

        ProjectVo projectVo = new ProjectVo();

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
}
