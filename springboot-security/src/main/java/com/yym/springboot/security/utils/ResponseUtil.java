package com.yym.springboot.security.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * response往页面写信息的工具类
 */
public class ResponseUtil {

    public static void genResponse(HttpServletResponse response, Map map) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }
}
