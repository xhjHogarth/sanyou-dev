package com.sanyou.service;

import com.sanyou.pojo.vo.ContractVo;
import com.sanyou.pojo.vo.ProjectVo;

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
    List<ProjectVo> getProjectList(String userId);

    /**
     * 根据项目Id，合同Id获取合同详情信息
     * @param projectId 项目Id
     * @param contractId 合同Id
     * @return
     */
    ContractVo getContractDetail(String projectId, String contractId);
}
