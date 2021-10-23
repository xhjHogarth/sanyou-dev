package com.sanyou.service.impl;

import com.sanyou.mapper.ContractDataMapper;
import com.sanyou.mapper.ProjectDataMapper;
import com.sanyou.mapper.VerticalityDataMapper;
import com.sanyou.pojo.ContractData;
import com.sanyou.pojo.ProjectData;
import com.sanyou.pojo.VerticalityData;
import com.sanyou.pojo.vo.ContractVo;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProjectVo> getProjectList(String userId) {
        List<ProjectVo> projectList = new ArrayList<>();
        List<ProjectData> projectDataList = projectDataMapper.getProjectList(userId);

        int count = 1;
        for (ProjectData projectData : projectDataList) {
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(projectData,projectVo);

            projectVo.setProjectName("项目" + count);
            count++;

            Example example = new Example(ContractData.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("projectId",projectData.getProjectId());
            List<ContractData> contractDataList = contractDataMapper.selectByExample(example);

            projectVo.setContractDataList(contractDataList==null?new ArrayList<>():contractDataList);
            projectList.add(projectVo);
        }

        return projectList;
    }

    @Override
    public ContractVo getContractDetail(String projectId, String contractId) {

        ContractVo contractVo = new ContractVo();

        Example example = new Example(ContractData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("contractId",contractId);
        criteria.andEqualTo("projectId",projectId);
        List<ContractData> contractDataList = contractDataMapper.selectByExample(example);
        if(contractDataList == null || contractDataList.size() == 0){
            contractVo.setProjectId(projectId);
            contractVo.setContractId(contractId);
            return contractVo;
        }
        BeanUtils.copyProperties(contractDataList.get(0),contractVo);

        Example example2 = new Example(VerticalityData.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("projectId",projectId);
        criteria2.andEqualTo("contractId",contractId);
        List<VerticalityData> verticalityDataList = verticalityDataMapper.selectByExample(example2);

        if(verticalityDataList != null && verticalityDataList.size() > 0){
            List<VerticalityData> sortedList = verticalityDataList.stream().sorted(Comparator.comparing(VerticalityData::getVerticalityId)).collect(Collectors.toList());
            contractVo.setCodeStart(sortedList.get(0).getVerticalityId());
            contractVo.setCodeEnd(sortedList.get(sortedList.size()-1).getVerticalityId());

            contractVo.setTotalNum(sortedList.size());

            //TODO 不同的state代表什么?
            contractVo.setUsingNum((int)sortedList.stream().filter(t -> t.getState()==0).count());
            contractVo.setMaintainNum((int)sortedList.stream().filter(t -> t.getState()==1).count());
            contractVo.setReserveNum((int)sortedList.stream().filter(t -> t.getState()==2).count());
            contractVo.setDeprecatedNum((int)sortedList.stream().filter(t -> t.getState()==3).count());
        }

        return contractVo;
    }
}
