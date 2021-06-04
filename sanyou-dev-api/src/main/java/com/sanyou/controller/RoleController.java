package com.sanyou.controller;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.Role;
import com.sanyou.service.RoleService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

/**
 * User: asus
 * Date: 2021/5/26
 * Time: 20:41
 * Version:V1.0
 */
@Api(value = "角色相关的接口",tags = {"角色相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping("/addRole")
    public JSONResult addRole(@RequestBody Role role){

        if(role == null || StringUtils.isBlank(role.getRoleCode()) || StringUtils.isBlank(role.getTitle()))
            return JSONResult.errorMsg("角色编码或标题不能为空!");

        Role role1 = roleService.findByCodeOrTitle(role);

        if(role1 == null){

            roleService.addRole(role);

            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("角色编码或标题已存在!");
        }
    }


    @ApiOperation(value = "批量修改角色", notes = "批量修改角色")
    @PostMapping("/updateRoles")
    public JSONResult updateRoles(@RequestBody List<Role> roles){

        if(roles == null || roles.size() == 0)
            return JSONResult.ok();

        roleService.updateRoles(roles);

        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name="query",value = "查询条件",required = false,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看角色列表", notes = "查看角色列表")
    @GetMapping("/query")
    public JSONResult query(String query, Integer page, Integer pageSize){
        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult result = roleService.query(query,page,pageSize);

        return JSONResult.ok(result);
    }


    @ApiImplicitParam(name="roleId",value = "角色id",required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "获取角色权限", notes = "获取角色权限")
    @GetMapping("/queryAuth")
    public JSONResult queryAuth(String roleId){

        if(StringUtils.isBlank(roleId))
            return JSONResult.errorMsg("角色id为空");

        List<Resource> list = roleService.queryAuth(roleId);

        return JSONResult.ok(list);
    }

    @ApiImplicitParam(name = "roleId", value = "角色id", required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "分配角色权限", notes = "分配角色权限")
    @PostMapping("/assignAuth")
    public JSONResult assignAuth(@RequestBody List<Resource> resources, String roleId){

        if(StringUtils.isBlank(roleId))
            return JSONResult.errorMsg("角色id为空!");

        roleService.assignAuth(resources, roleId);

        return JSONResult.ok();
    }

    @ApiOperation(value = "获取所有角色", notes = "获取所有角色")
    @GetMapping("/getAll")
    public JSONResult getAll(){

        List<Role> list = roleService.getAll();

        return JSONResult.ok(list);
    }
}
