package com.sanyou.controller;

import com.sanyou.pojo.User;
import com.sanyou.pojo.UserEquipment;
import com.sanyou.pojo.UserSession;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.service.UserService;
import com.sanyou.service.UserSessionService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.MD5Utils;
import com.sanyou.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sanyou.controller.BasicController.PAGE_SIZE;

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

    @Autowired
    private UserSessionService userSessionService;


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public JSONResult login(@RequestBody User user, HttpServletRequest request) throws Exception {

        if(user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        //判断用户名和密码是否正确
        User loginUser = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));

        if(loginUser != null){
            if(loginUser.getEnableMark() == 0)
                return JSONResult.errorMsg("当前用户已被禁用!");

            String remoteAddr = request.getRemoteAddr();
            loginUser.setLastLoginIp(remoteAddr);
            loginUser.setLastLoginTime(new Date());

            List<User> userList = new ArrayList<>();
            userList.add(loginUser);
            userService.updateUserInfo(userList);

            //如果session存在,则更新session;如果session不存在,则创建session
            UserSession userSession = new UserSession();
            userSession.setUserId(loginUser.getId());
            userSession.setCreatetime(new Date());
            String session = loginUser.getId() + "_" + UUID.randomUUID();
            userSession.setSession(session);
            userSessionService.updateSession(userSession);

            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(loginUser,userVo);
            userVo.setSession(session);
            return JSONResult.ok(userVo);
        }else{
            return JSONResult.errorMsg("用户名或密码不正确,请重试!");
        }
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户信息", required = true,
                    dataType = "object", paramType = "body")})
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @PostMapping("/add")
    public JSONResult addUser(@RequestBody User user, HttpServletRequest request) throws Exception {
        if(user == null)
            return JSONResult.errorMsg("用户对象为空");

        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()))
            return JSONResult.errorMsg("用户名或密码不能为空");


        User usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if(usernameIsExist == null){
            String remoteAddr = request.getRemoteAddr();

            user.setRegistIp(remoteAddr);
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
    public JSONResult updateUserInfo(@RequestBody List<User> users){

        if(users == null || users.size() == 0){
            return JSONResult.ok();
        }

        userService.updateUserInfo(users);

        return JSONResult.ok();
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户", required = true,
            dataType = "object", paramType = "body")})
    @ApiOperation(value = "删除用户",notes = "删除用户")
    @PostMapping("/deleteUser")
    public JSONResult deleteUser(@RequestBody List<User> users){

        if(users == null || users.size() == 0){
            return JSONResult.ok();
        }

        userService.updateUserInfo(users);

        return JSONResult.ok();
    }



    @ApiImplicitParams({@ApiImplicitParam(name="page",value = "分页数",required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,
                    dataType = "int", paramType = "query")})
    @ApiOperation(value = "查看用户列表", notes = "查看用户列表")
    @PostMapping("/query")
    public JSONResult query(@RequestBody UserVo userVo, Integer page, Integer pageSize){

        if(page == null)
            page = 1;

        if(pageSize == null)
            pageSize = PAGE_SIZE;

        PagedResult pagedResult = userService.query(userVo,page,pageSize);

        return JSONResult.ok(pagedResult);
    }


    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "分配用户设备权限", notes = "分配用户设备权限")
    @PostMapping("/assignEquip")
    public JSONResult assignEquip(@RequestBody List<UserEquipment> userEquipments,String userId){

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户id为空!");

        userService.assignEquip(userEquipments,userId);

        return JSONResult.ok();
    }


    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "string", paramType = "query")
    @ApiOperation(value = "下载用户信息", notes = "下载用户信息")
    @PostMapping("/downloadUserInfo")
    public JSONResult downloadUserInfo(String userId){

        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户id为空!");

        // TODO 用户资料下载的具体实现

        return JSONResult.ok();
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/updatePwd")
    public JSONResult updatePwd(@RequestBody UserVo userVo) throws Exception {

        if (userVo == null || StringUtils.isBlank(userVo.getId()))
            return JSONResult.errorMsg("用户id为空!");

        if(StringUtils.isBlank(userVo.getOldPwd()))
            return JSONResult.errorMsg("旧密码为空!");

        if(StringUtils.isBlank(userVo.getNewPwd()))
            return JSONResult.errorMsg("新密码为空!");

        User user = userService.getUserById(userVo.getId());
        if(user == null)
            return JSONResult.errorMsg("用户id错误!");
        else{
            if(user.getPassword().equals(MD5Utils.getMD5Str(userVo.getOldPwd()))){
                User updateUser = new User();
                updateUser.setId(userVo.getId());
                updateUser.setPassword(MD5Utils.getMD5Str(userVo.getNewPwd()));
                List<User> list = new ArrayList<>();
                list.add(updateUser);
                userService.updateUserInfo(list);
            }else{
                return JSONResult.errorMsg("旧密码不正确!");
            }
        }

        return JSONResult.ok();
    }
}
