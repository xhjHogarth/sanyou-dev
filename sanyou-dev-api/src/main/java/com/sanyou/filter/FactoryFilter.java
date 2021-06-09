package com.sanyou.filter;

import com.sanyou.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: asus
 * Date: 2021/6/8
 * Time: 17:02
 * Version:V1.0
 */
public class FactoryFilter implements Filter {

    @Autowired
    private FactoryService factoryService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化filter时手动注入bean对象
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
        factoryService = ac.getBean(FactoryService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FactoryFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
