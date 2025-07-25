package com.itxuan.service.Impl;

import com.itxuan.annotation.Log;
import com.itxuan.mapper.EmpLogMapper;
import com.itxuan.pojo.EmpLog;
import com.itxuan.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;


    @Log
    @Transactional(propagation = Propagation.REQUIRES_NEW)//有事务，这个方法也会创建一个新的事务，旧事务被挂起
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}