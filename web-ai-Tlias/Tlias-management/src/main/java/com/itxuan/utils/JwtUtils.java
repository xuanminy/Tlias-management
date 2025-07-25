package com.itxuan.utils;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    // 生成JWT令牌
    public static String generateToken(Map<String, Object> claims) {
        String base64SecretKey = "itxuan";
        String key = Base64Util.encode(base64SecretKey);
        return Jwts.builder()
                .setClaims(claims) // 添加负载数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000*3600*12)) // 设置过期时间，默认1小时
                .signWith(SignatureAlgorithm.HS256,key) // 设置签名密钥
                .compact(); // 生成字符串
    }

    // 解析JWT令牌
    public static Claims parseToken(String token) {
        String base64SecretKey = "itxuan";
        String key = Base64Util.encode(base64SecretKey);
        try {
            return Jwts.parser()
                    .setSigningKey( key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("JWT解析失败: " + e.getMessage(), e);
        }
    }

}