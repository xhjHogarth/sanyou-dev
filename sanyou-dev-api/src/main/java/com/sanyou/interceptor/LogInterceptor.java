package com.sanyou.interceptor;

import com.sanyou.pojo.Resource;
import com.sanyou.pojo.User;
import com.sanyou.pojo.UserLog;
import com.sanyou.service.ResourceService;
import com.sanyou.service.UserLogService;
import com.sanyou.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/9
 * Time: 20:51
 * Version:V1.0
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        UserLogService userLogService = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(UserLogService.class);
        ResourceService resourceService = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(ResourceService.class);
        UserService userService = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(UserService.class);

        String userId = request.getHeader("userId");
        String username = request.getHeader("username");
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();

        List<String> excludesPath = Arrays.asList(
                "/factory/getAll","/factory/querySubFactory",
                "/userGroup/queryRoles","/userGroup/getAll",
                "/role/getAll","/role/queryAuth",
                "/resource/getAll",
                "/equipment/getEquipmentTree","/equipment/getUserEquip",
                "/userLog/query",
                "/industryData/getPieChart","/industryData/getBarChart","/industryData/getLineChart","/industryData/queryData","/industryData/deleteData",
                "/industryData/query","/industryData/getCycleLineChart","/industryData/getNormalLineChart","/industryData/getNormalBarChart"
        );
        if(excludesPath.indexOf(url) > -1){
            return;
        }


        String module = url.split("/")[1];

        UserLog userLog = new UserLog();
        userLog.setModule(module);
        userLog.setIp(ip);
        userLog.setUrl(url);
        userLog.setDeleteMark((byte)0);
        userLog.setCreatetime(new Date());

        if("/user/login".equals(url)){
            userLog.setAction("用户登录");
        }else{
            Resource resource = resourceService.findByUrl(url);
            if(resource != null)
                userLog.setAction(resource.getTitle());
        }

        if(StringUtils.isNotBlank(username)){
            User user = userService.queryUsernameIsExist(username);
            if(user != null){
                userLog.setUserid(user.getId());
                userLogService.addUserLog(userLog);
            }
        }else if(StringUtils.isNotBlank(userId)){
            userLog.setUserid(userId);
            userLogService.addUserLog(userLog);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
