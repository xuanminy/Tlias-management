package com.itxuan.service;


import com.itxuan.pojo.EmpLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface EmpLogService {

    public void insertLog(EmpLog empLog);

}