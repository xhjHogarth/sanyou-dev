package com.sanyou.controller.app;

import com.sanyou.pojo.vo.ContractVo;
import com.sanyou.pojo.vo.ProjectVo;
import com.sanyou.service.ProjectService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: asus
 * Date: 2021-10-10
 * Time: 12:56
 * Version:V1.0
 */
@Api(value = "项目相关的接口",tags = {"项目相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping("/getProjectList")
    public JSONResult getProjectList(String userId){
        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("用户Id为空!");
        }

        List<ProjectVo> projectList = projectService.getProjectList(userId);

        return JSONResult.ok(projectList);
    }

    @GetMapping("/getContractDetail")
    public JSONResult getContractDetail(String projectId,String contractId){
        if(StringUtils.isBlank(projectId))
            return JSONResult.errorMsg("项目Id为空!");

        if(StringUtils.isBlank(contractId))
            return JSONResult.errorMsg("合同Id为空!");


        ContractVo contractVo = projectService.getContractDetail(projectId,contractId);

        return JSONResult.ok(contractVo);
    }
}
