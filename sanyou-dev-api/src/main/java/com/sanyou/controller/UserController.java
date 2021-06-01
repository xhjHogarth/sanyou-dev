package com.sanyou.controller;

import com.sanyou.pojo.User;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.service.UserService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * User: asus
 * Date: 2021/5/25
 * Time: 18:56
 * Version:V1.0
 */
@Api(value = "用户相关的接口",tags = {"用户相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name="username",value = "用户名",required = true,
            dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="password",value = "密码",required = true,
                    dataType = "String", paramType = "query")})
    @PostMapping("/login")
    public JSONResult login(String username,String password) throws Exception {

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        User user = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if(user != null){
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return JSONResult.ok(userVo);
        }else{
            return JSONResult.errorMsg("用户名或密码不正确,请重试!");
        }
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户信息", required = true,
                    dataType = "object", paramType = "body")})
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @PostMapping("/add")
    public JSONResult addUser(@RequestBody User user) throws Exception {

        if(user == null)
            return JSONResult.errorMsg("用户对象为空");

        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()))
            return JSONResult.errorMsg("用户名或密码不能为空");


        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if(!usernameIsExist){
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setEnableMark((byte) 1);
            user.setDeleteMark((byte)0);
            user.setCreatetime(new Date());
            userService.addUser(user);
        }else{
            return JSONResult.errorMsg("用户名已经存在,请换一个!");
        }

        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户ID", required = true,
            dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "mark", value = "启用/禁用标志", required = true,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "启用/禁用用户", notes = "启用/禁用用户")
    @PostMapping("/enableOrUnEnableUser")
    public JSONResult enableOrUnEnableUser(String userId , int mark){

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户id为空");

        userService.enableOrUnEnableUser(userId, mark);

        return JSONResult.ok();
    }


    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户信息", required = true,
            dataType = "object", paramType = "body")})
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    @PostMapping("/updateUserInfo")
    public JSONResult updateUserInfo(@RequestBody User user){

        if(user == null || StringUtils.isBlank(user.getId())){
            return JSONResult.errorMsg("用户id不能为空!");
        }

        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if(usernameIsExist){
            user.setUpdatetime(new Date());
            User newUser = userService.updateUserInfo(user);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(newUser,userVo);
            return JSONResult.ok(userVo);
        }else{
            return JSONResult.errorMsg("用户不存在!");
        }
    }

}
