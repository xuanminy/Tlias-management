package com.itxuan.service.Impl;

import com.itxuan.mapper.EmpMapper;
import com.itxuan.pojo.Emp;
import com.itxuan.pojo.LoginInfo;
import com.itxuan.service.LoginService;
import com.itxuan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;



    @Override
    public LoginInfo login(Emp emp) {
        Emp emp1 = empMapper.login(emp);

        if (emp1 != null){
            log.info("登录：{}",emp1);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",emp1.getId());
            claims.put("username",emp1.getUsername());
            String token = JwtUtils.generateToken(claims);
            return new LoginInfo(emp1.getId(),emp1.getUsername(),emp1.getName(),token);
        }
        return null;
    }
}
