package com.sanyou.controller;

import com.sanyou.pojo.Project;
import com.sanyou.pojo.ProjectFile;
import com.sanyou.pojo.vo.OrderVo;
import com.sanyou.service.OrderService;
import com.sanyou.service.ProjectFileService;
import com.sanyou.service.ProjectService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-12-10
 * Time: 18:59
 * Version:V1.0
 */
@Api(value = "项目相关的接口",tags = {"项目相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectFileService projectFileService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/addProject2")
    public JSONResult addProject2(@RequestBody Project project){
        if(project == null || StringUtils.isBlank(project.getProjectName()))
            return JSONResult.errorMsg("项目名不能为空!");

        if(StringUtils.isBlank(project.getProjectCode()))
            return JSONResult.errorMsg("项目编码不能为空!");

        if(StringUtils.isBlank(project.getUserId()))
            return JSONResult.errorMsg("用户Id不能为空!");

        if(projectService.checkNameAndCode(project)){
            return JSONResult.errorMsg("项目名或编码已存在!");
        }

        projectService.addProject(project);

        return JSONResult.ok();
    }

    @PostMapping("/addProject")
    public JSONResult addProject(HttpServletRequest request){
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        String projectName = request.getParameter("projectName");
        String projectCode = request.getParameter("projectCode");
        String factoryId = request.getParameter("factoryId");
        String userId = request.getParameter("userId");
        String ddbLength = request.getParameter("ddbLength");
        String ddbWidth = request.getParameter("ddbWidth");
        String ddbHeight = request.getParameter("ddbHeight");
        String yjbLength = request.getParameter("yjbLength");
        String yjbWidth = request.getParameter("yjbWidth");
        String yjbHeight = request.getParameter("yjbHeight");


        if(StringUtils.isBlank(projectName))
            return JSONResult.errorMsg("项目名不能为空!");

        if(StringUtils.isBlank(projectCode))
            return JSONResult.errorMsg("项目编码不能为空!");

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id不能为空!");

        if(StringUtils.isBlank(ddbLength) || StringUtils.isBlank(ddbWidth) || StringUtils.isBlank(ddbHeight))
            return JSONResult.errorMsg("导电棒尺寸不能为空!");

        if(StringUtils.isBlank(yjbLength) || StringUtils.isBlank(yjbWidth) || StringUtils.isBlank(yjbHeight))
            return JSONResult.errorMsg("阴极板尺寸不能为空!");

        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectCode(projectCode);
        project.setUserId(userId);
        if(StringUtils.isNotBlank(factoryId))
            project.setFactoryId(factoryId);
        project.setDdbLength(Double.valueOf(ddbLength));
        project.setDdbWidth(Double.valueOf(ddbWidth));
        project.setDdbHeight(Double.valueOf(ddbHeight));
        project.setYjbLength(Double.valueOf(yjbLength));
        project.setYjbWidth(Double.valueOf(yjbWidth));
        project.setYjbHeight(Double.valueOf(yjbHeight));

        if(projectService.checkNameAndCode(project)){
            return JSONResult.errorMsg("项目名或编码已存在!");
        }

        int projectId = projectService.addProject(project);

        if(file!=null){
            try {
                //原始文件名
                String originalFilename = file.getOriginalFilename();
                //存储的文件名
                String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                //扩展名
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                //存储的相对路径
                String path = "/upload/pdf/";
                //绝对路径
                String url = "/home/data/www/web/sanyou" + path;
                File dir = new File(url);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                file.transferTo(new File(url + filename + extension));

                ProjectFile projectFile = new ProjectFile();
                projectFile.setProjectId(projectId);
                projectFile.setExtension(extension);
                projectFile.setFilename(filename + extension);
                projectFile.setPath(url);
                projectFile.setRealname(originalFilename);
                projectFile.setUploadtime(new Date());

                projectFileService.add(projectFile);
            } catch (IOException e) {
                return JSONResult.errorMsg("文件上传失败");
            }
        }

        return JSONResult.ok();
    }

    @PostMapping("/updateProject")
    public JSONResult updateProject(HttpServletRequest request){
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        String id = request.getParameter("id");
        String projectName = request.getParameter("projectName");
        String projectCode = request.getParameter("projectCode");
        String factoryId = request.getParameter("factoryId");
        String ddbLength = request.getParameter("ddbLength");
        String ddbWidth = request.getParameter("ddbWidth");
        String ddbHeight = request.getParameter("ddbHeight");
        String yjbLength = request.getParameter("yjbLength");
        String yjbWidth = request.getParameter("yjbWidth");
        String yjbHeight = request.getParameter("yjbHeight");

        if(StringUtils.isBlank(id))
            return JSONResult.errorMsg("项目不存在!");

        if(StringUtils.isBlank(projectName))
            return JSONResult.errorMsg("项目名不能为空!");

        if(StringUtils.isBlank(projectCode))
            return JSONResult.errorMsg("项目编码不能为空!");

        if(StringUtils.isBlank(ddbLength) || StringUtils.isBlank(ddbWidth) || StringUtils.isBlank(ddbHeight))
            return JSONResult.errorMsg("导电棒尺寸不能为空!");

        if(StringUtils.isBlank(yjbLength) || StringUtils.isBlank(yjbWidth) || StringUtils.isBlank(yjbHeight))
            return JSONResult.errorMsg("阴极板尺寸不能为空!");

        Project project = new Project();
        project.setId(Integer.valueOf(id));
        project.setProjectName(projectName);
        project.setProjectCode(projectCode);
        if(StringUtils.isNotBlank(factoryId))
            project.setFactoryId(factoryId);
        project.setDdbLength(Double.valueOf(ddbLength));
        project.setDdbWidth(Double.valueOf(ddbWidth));
        project.setDdbHeight(Double.valueOf(ddbHeight));
        project.setYjbLength(Double.valueOf(yjbLength));
        project.setYjbWidth(Double.valueOf(yjbWidth));
        project.setYjbHeight(Double.valueOf(yjbHeight));

        if(projectService.checkNameAndCode(project)){
            return JSONResult.errorMsg("项目名或编码已存在!");
        }

        projectService.updateProject(project);

        if(file!=null){
            try {
                //原始文件名
                String originalFilename = file.getOriginalFilename();
                //存储的文件名
                String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                //扩展名
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                //存储的相对路径
                String path = "/upload/pdf/";
                //绝对路径
                String url = "/home/data/www/web/sanyou" + path;
                File dir = new File(url);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                file.transferTo(new File(url + filename + extension));

                ProjectFile projectFile = new ProjectFile();
                projectFile.setProjectId(Integer.valueOf(id));
                projectFile.setExtension(extension);
                projectFile.setFilename(filename + extension);
                projectFile.setPath(url);
                projectFile.setRealname(originalFilename);
                projectFile.setUploadtime(new Date());

                projectFileService.update(projectFile);
            } catch (IOException e) {
                return JSONResult.errorMsg("文件上传失败");
            }
        }

        return JSONResult.ok();
    }

    @PostMapping("/deleteProject")
    public JSONResult deleteProject(Integer pid){
        if(pid == null)
            return JSONResult.errorMsg("项目不存在!");

        projectService.deleteProject(pid);

        return JSONResult.ok();
    }

    @GetMapping("/query")
    public JSONResult query(String query,Integer page, Integer pageSize){
        PagedResult result = projectService.query(query,page,pageSize);
        return JSONResult.ok(result);
    }

    @GetMapping("/downloadProjectPDF")
    private JSONResult downloadProjectPDF(Integer pid){
        if(pid == null)
            return JSONResult.errorMsg("项目图纸不存在!");

        ProjectFile projectFile = projectFileService.findByPid(pid);
        if(projectFile == null)
            return JSONResult.errorMsg("项目图纸不存在!");

        return JSONResult.ok(projectFile.getFilename());
    }

    @GetMapping("/getAll")
    private JSONResult getAll(){
        List<Project> projectList = projectService.getAll();
        return JSONResult.ok(projectList);
    }


    @GetMapping("/getProjectOrder")
    public JSONResult getProjectOrder(Integer pid){
        if(pid == null)
            return JSONResult.errorMsg("项目不存在!");

        List<OrderVo> orderVoList = orderService.getProjectOrder(pid);
        return JSONResult.ok(orderVoList);
    }
}
