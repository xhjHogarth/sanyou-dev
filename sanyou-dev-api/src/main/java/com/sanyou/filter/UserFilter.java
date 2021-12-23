package com.sanyou.filter;

import com.sanyou.service.UserService;
import com.sanyou.utils.JSONResult;
import com.sanyou.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * User: asus
 * Date: 2021/6/8
 * Time: 17:21
 * Version:V1.0
 */
public class UserFilter implements Filter {

    private UserService userService;

    private List<String> excludesPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化filter时手动注入bean对象
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
        userService = ac.getBean(UserService.class);

        excludesPath = Arrays.asList(
                "/factory/getAll","/factory/querySubFactory",
                "/userGroup/queryRoles","/userGroup/getAll",
                "/role/getAll","/role/queryAuth",
                "/resource/getAll",
                "/equipment/getEquipmentTree","/equipment/getUserEquip","/equipment/getEquip","/equipment/getEquipById",
                "/userLog/download","/user/login","/user/updatePwd","/user/download","/user/register",
                "/project/getAll","/product/copyData"
        );

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userId = request.getHeader("userId");
        String url = request.getRequestURI();

        System.out.println("UserFilter:" + url);

        if(excludesPath.indexOf(url)>-1){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if(StringUtils.isNotBlank(userId)){
            boolean hasAuth = userService.checkAuth(userId,url);
            if(hasAuth){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                ResponseUtils.returnErrorResponse((HttpServletResponse) servletResponse,
                        JSONResult.build(503,"没有权限",null));
            }
        }else{
            ResponseUtils.returnErrorResponse((HttpServletResponse) servletResponse,
                    JSONResult.build(503,"用户id为空!",null));
        }
    }

    @Override
    public void destroy() {

    }
}
