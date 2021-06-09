package com.sanyou.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: asus
 * Date: 2021/5/25
 * Time: 19:19
 * Version:V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        // String userId = request.getHeader("userId");
        // String userToken = request.getHeader("userToken");
        //
        // if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)){
        //     String uniqueToken = redis.getSession(USER_REDIS_SESSION + ":" + userId);
        //     if(StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)){
        //         returnErrorResponse(response, new JSONResult().errorTokenMsg("请登录..."));
        //         return false;
        //     }else {
        //         if(!uniqueToken.equals(userToken)){
        //             returnErrorResponse(response, new JSONResult().errorTokenMsg("账号被挤出..."));
        //             return false;
        //         }
        //     }
        // }else{
        //     returnErrorResponse(response, new JSONResult().errorTokenMsg("请登录..."));
        //     return false;
        // }

        // System.out.println("LoginInterceptor");
        return true;

    }

    // public void returnErrorResponse(HttpServletResponse response, JSONResult result)
    //         throws IOException, UnsupportedEncodingException {
    //     OutputStream out=null;
    //     try{
    //         response.setCharacterEncoding("utf-8");
    //         response.setContentType("text/json");
    //         out = response.getOutputStream();
    //         out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
    //         out.flush();
    //     } finally{
    //         if(out!=null){
    //             out.close();
    //         }
    //     }
    // }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
