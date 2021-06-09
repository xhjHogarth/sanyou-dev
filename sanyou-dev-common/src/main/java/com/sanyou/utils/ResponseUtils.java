package com.sanyou.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * User: asus
 * Date: 2021/6/8
 * Time: 20:15
 * Version:V1.0
 */
public class ResponseUtils {

    public static void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,userId");
            response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }
}
