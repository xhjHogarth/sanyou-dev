package com.sanyou.controller.app;

import com.sanyou.pojo.User;
import com.sanyou.pojo.vo.UserVo;
import com.sanyou.service.UserService;
import com.sanyou.utils.JSONResult;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021-10-12
 * Time: 15:05
 * Version:V1.0
 */
@Api(value = "APP用户信息相关的接口",tags = {"APP用户信息相关的Controller"})
@CrossOrigin
@RestController
@RequestMapping("/app/userInfo")
public class UserInfoController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUserInfo")
    public JSONResult getUserInfo(String userId){
        if(StringUtils.isBlank(userId))
            return JSONResult.errorMsg("用户Id为空");
        else{
            UserVo userVo = userService.getUserInfo(userId);
            return JSONResult.ok(userVo);
        }
    }

    @PostMapping("/updateUserInfo")
    public JSONResult updateUserInfo(@RequestBody User user){
        if(user == null || StringUtils.isBlank(user.getId()))
            return JSONResult.errorMsg("用户Id为空!");

        user.setUpdatetime(new Date());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userService.updateUserInfo(userList);

        return JSONResult.ok();
    }
}
