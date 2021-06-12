package com.sanyou.controller;

import com.sanyou.pojo.Role;
import com.sanyou.pojo.Usergroup;
import com.sanyou.service.UserGroupService;
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
 * Date: 2021/6/1
 * Time: 17:36
 * Version:V1.0
 */
@Api(value = "用户组相关的接口",tags = {"用户组相关的Controller"})
@RestController
@CrossOrigin
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @ApiOperation(value = "添加用户组", notes = "添加用户组")
    @PostMapping("/addGroup")
    public JSONResult addGroup(@RequestBody Usergroup usergroup){

        if(usergroup == null || StringUtils.isBlank(usergroup.getGroupName()))
            return JSONResult.errorMsg("用户组名称为空!");

        if(usergroup.getGroupLevel() == null)
            return JSONResult.errorMsg("用户组级别为空!");

        userGroupService.addGroup(usergroup);

        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name="query",value = "查询条件",required = false,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看用户组列表", notes = "查看用户组列表")
    @GetMapping("/query")
    public JSONResult query(String query, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult pagedResult = userGroupService.query(query, page, pageSize);

        return JSONResult.ok(pagedResult);
    }

    @ApiImplicitParam(name="groupId",value = "用户组id",required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "获取用户组角色", notes = "获取用户组角色")
    @GetMapping("/queryRoles")
    public JSONResult queryRoles(String groupId){
        if(StringUtils.isBlank(groupId))
            return JSONResult.errorMsg("用户组id为空!");

        List<Role> list = userGroupService.queryRoles(groupId);

        return JSONResult.ok(list);
    }


    @ApiOperation(value = "修改用户组信息", notes = "修改用户组信息")
    @PostMapping("/updateUserGroup")
    public JSONResult updateUserGroup(@RequestBody Usergroup usergroup){

        if(usergroup == null || StringUtils.isBlank(usergroup.getId()))
            return JSONResult.errorMsg("用户组id为空!");

        if(usergroup.getGroupType() == 1 && usergroup.getDeleteMark() != null &&
                usergroup.getDeleteMark() == 1)
            return JSONResult.errorMsg("系统组用户不允许删除!");

        userGroupService.updateUserGroup(usergroup);

        return JSONResult.ok();
    }

    @ApiOperation(value = "删除用户组", notes = "删除用户组")
    @PostMapping("/deleteUserGroup")
    public JSONResult deleteUserGroup(@RequestBody Usergroup usergroup){

        if(usergroup == null || StringUtils.isBlank(usergroup.getId()))
            return JSONResult.errorMsg("用户组id为空!");

        if(usergroup.getGroupType() == 1 && usergroup.getDeleteMark() != null &&
                usergroup.getDeleteMark() == 1)
            return JSONResult.errorMsg("系统组用户不允许删除!");

        userGroupService.updateUserGroup(usergroup);

        return JSONResult.ok();
    }

    @ApiImplicitParam(name="groupId",value = "用户组id",required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "分配用户组角色", notes = "分配用户组角色")
    @PostMapping("/assignRoles")
    public JSONResult assignRoles(@RequestBody List<Role> roles, String groupId){

        if(StringUtils.isBlank(groupId))
            return JSONResult.errorMsg("用户组id为空!");

        userGroupService.assignRoles(roles,groupId);

        return JSONResult.ok();
    }

    @ApiOperation(value = "获取所有的用户组", notes = "获取所有的用户组")
    @GetMapping("/getAll")
    public JSONResult getAll(){

        List<Usergroup> userGroups = userGroupService.getAll();

        return JSONResult.ok(userGroups);
    }
}
