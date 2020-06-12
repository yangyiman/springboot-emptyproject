package com.yym.springboot.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 认证过滤器
 */
public class JwtUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 从请求体中获取username和password,然后生成token传递出去
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String body = null;
        try {
            body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
            String username = null, password = null;
            if (StringUtils.isNotBlank(body)) {
                JSONObject jsonObject = JSON.parseObject(body);
                username = jsonObject.getString("username") == null ? "" : jsonObject.getString("username");
                password = jsonObject.getString("password") == null ? "" : jsonObject.getString("password");
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
                return this.getAuthenticationManager().authenticate(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
