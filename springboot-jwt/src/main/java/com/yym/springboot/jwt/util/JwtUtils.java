package com.yym.springboot.jwt.util;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class JwtUtils {

    public static final String JWT_KEY = "yym-test";


    // 生成token
    public static String generateToken(String subject){
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("yym");
        builder.setSubject(subject);
        // 设置过期时间,30分钟
        builder.setExpiration(new Date(System.currentTimeMillis()+1800000));
        // 设置颁发时间
        builder.setIssuedAt(new Date());
        // 加盐
        builder.signWith(SignatureAlgorithm.HS256,JWT_KEY);
        return builder.compact();
    }

    // 解析token
    public static Claims parseToken(String token){
        if(StringUtils.isBlank(token)){
            throw new RuntimeException("token为空");
        }
        JwtParser parser = Jwts.parser();
        try {
            Claims claims = parser.setSigningKey(JWT_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("token过期");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("不支持的token");
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new RuntimeException("token被篡改");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token解析错误");
        }
        return null;
    }
}
