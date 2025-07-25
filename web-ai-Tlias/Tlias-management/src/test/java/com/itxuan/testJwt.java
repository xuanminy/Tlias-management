package com.itxuan;

import com.itxuan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class testJwt {


    @Test
    public void testGerenatejwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "songjiang");
        dataMap.put("password", "123456");
        //生成一个JWT令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXR4dWFu")//指定加密算法和密钥
                .addClaims(dataMap)//添加自定义信息--是一个Map集合
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600*1000))//设置令牌过期时间1个小时，默认单位是毫秒
                .compact();//生成令牌
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("aXR4dWFu")//设置密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoxLCJ1c2VybmFtZSI6InNvbmdqaWFuZyIsImV4cCI6MTc1MzMzMjYzMn0._8VfKLzvYeZnWHHZNIIdET3YhzF8IMBz4bZz6oOJyKM")//解析JWT令牌
                .getBody();//返回的Claims对象，其实就是封装了Map集合的Claims--本质上是一个Map集合
        System.out.println(claims);//报错的情况：篡改令牌，令牌过期，令牌格式错误，令牌签名错误，令牌无效
    }

    @Test
    public void testJwtUtils() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "songjiang");
        dataMap.put("password", "123456");
        //生成一个JWT令牌
        String jwt = JwtUtils.generateToken(dataMap);
        System.out.println(jwt);
        //解析JWT令牌
        Claims claims = JwtUtils.parseToken(jwt);
        System.out.println( claims);
    }
}
