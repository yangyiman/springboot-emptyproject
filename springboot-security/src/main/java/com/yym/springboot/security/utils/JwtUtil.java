package com.yym.springboot.security.utils;

import com.yym.springboot.security.domain.JwtUser;
import com.yym.springboot.security.entity.JdUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt的工具类
 */
public class JwtUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SALT = "@!securitysalt!@";
    public static final Integer TOKEN_EXPIRE_SECOND = 7200;


    public static String generateToken(JdUser user){
        Map<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("role",user.getRole());
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256,TOKEN_SALT)
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRE_SECOND*1000))
                .compact();
    }

    public static String generateToken(JwtUser user){
        Map<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("role",user.getRole());
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256,TOKEN_SALT)
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRE_SECOND*1000))
                .compact();
    }


    public static Claims parseToken(String token){
        return Jwts.parser().setSigningKey(TOKEN_SALT).parseClaimsJws(token).getBody();
    }

    public static String getUsername(String token){
        //return parseToken(token).getSubject();
        return parseToken(token).get("username",String.class);
    }

    public static String getRole(String token){
        return parseToken(token).get("role").toString();
    }

    public static boolean isExpire(String token){
        Date expiration = parseToken(token).getExpiration();
        return new Date().after(expiration);
    }

    /*public static void main(String[] args) {
        JdUser user = new JdUser();
        user.setId(1).setUsername("tom").setPassword("123").setRole("USER");
        String token = JwtUtil.generateToken(user);
        System.out.println("token = " + token);

        Claims claims = JwtUtil.parseToken(token);
        System.out.println("claims = " + claims);

        boolean expire = JwtUtil.isExpire(token);
        System.out.println("expire = " + expire);

        String username = JwtUtil.getUsername(token);
        System.out.println("username = " + username);

        String role = JwtUtil.getRole(token);
        System.out.println("role = " + role);
    }*/
}
