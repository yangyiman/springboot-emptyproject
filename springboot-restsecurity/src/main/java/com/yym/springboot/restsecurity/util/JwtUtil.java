package com.yym.springboot.restsecurity.util;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.common.exception.CommonException;
import com.yym.springboot.restsecurity.securitypojo.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    public static final String TOKEN_HEADER = "Authorization";
    private static final String SALT = "!!@@yymspringsecurity@@!!";
    private static Integer EXPIRE_TIME_HOUR = 2;
    private static String row_token = null;

    /**
     * 生成token,有效期为2小时
     * @param jwtUser
     * @return
     */
    public static String genericToken(JwtUser jwtUser,Integer expireTimeHour){
        Map<String, Object> map = new HashMap<>();
        map.put("username",jwtUser.getUsername());
        map.put("permission",jwtUser.getPermissions());
        if(expireTimeHour != null){
            EXPIRE_TIME_HOUR = expireTimeHour;
        }
        String token = Jwts
                .builder()
                .setSubject(jwtUser.getUsername())
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256,SALT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME_HOUR * 60 * 60 * 1000))
                .compact();
        System.out.println("token = " + token);
        row_token = token;
        return token;
    }

    public static Claims parseToken(String token){
        if (StringUtils.isNotBlank(token)) {
            // 解析token
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(SALT)
                        .parseClaimsJws(token)
                        .getBody();
                return claims;
            } catch (ExpiredJwtException e) {
                throw new RuntimeException("token过期");
            }
        }
        throw new RuntimeException("token为空");
    }



}
