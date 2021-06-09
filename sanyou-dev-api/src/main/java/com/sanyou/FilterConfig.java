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
        bean.setUrlPatterns(Arrays.asList("/user/*"));
        return bean;
    }

    // @Override
    // public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    //     AnnotationConfigEmbeddedWebApplicationContext ac = (AnnotationConfigEmbeddedWebApplicationContext) applicationContext;
    //     FilterRegistrationBean bean = new FilterRegistrationBean();
    //     bean.setFilter(new UserFilter());
    //     bean.setOrder(1);
    //     bean.setUrlPatterns(Arrays.asList("/user/*"));
    //     GenericBeanDefinition bd = new GenericBeanDefinition();
    //     bd.setBeanClass(FilterRegistrationBean.class);
    //     ac.getBeanFactory().registerSingleton("filterRegistrationBean",bean);
    // }


    // @Bean
    // FilterRegistrationBean filterRegistrationBean2(){
    //     FilterRegistrationBean bean = new FilterRegistrationBean();
    //     bean.setFilter(new FactoryFilter());
    //     bean.setOrder(2);
    //     bean.setUrlPatterns(Arrays.asList("/factory/*"));
    //     return bean;
    // }
    //
    // @Bean
    // FilterRegistrationBean filterRegistrationBean3(){
    //     FilterRegistrationBean bean = new FilterRegistrationBean();
    //     bean.setFilter(new UserGroupFilter());
    //     bean.setOrder(3);
    //     bean.setUrlPatterns(Arrays.asList("/userGroup/*"));
    //     return bean;
    // }

}
