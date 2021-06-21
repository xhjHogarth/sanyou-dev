package com.sanyou;

import com.sanyou.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * User: asus
 * Date: 2021/6/8
 * Time: 16:55
 * Version:V1.0
 */
@Configuration
public class FilterConfig{

    @Bean
    FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new UserFilter());
        bean.setOrder(1);
        bean.setUrlPatterns(Arrays.asList("/user/*","/factory/*","/userGroup/*","/role/*","/resource/*","/userLog/*",
                "/equipment/*"));
        return bean;
    }

}
