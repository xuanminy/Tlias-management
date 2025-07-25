package com.itxuan.aop;

import com.itxuan.mapper.OperateLogMapper;
import com.itxuan.pojo.OperateLog;
import com.itxuan.utils.CurrentHolder;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class Log {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itxuan.annotation.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        Long end = System.currentTimeMillis();

        //组装参数,构建日志对象


        OperateLog OperateLog = new OperateLog();
        OperateLog.setOperateEmpId(getOperateEmpId());
        OperateLog.setOperateTime(LocalDateTime.now());
        OperateLog.setClassName(joinPoint.getTarget().getClass().getName());
        OperateLog.setMethodName(joinPoint.getSignature().getName());
        OperateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        OperateLog.setReturnValue(result != null ? result.toString() : "void");
        OperateLog.setCostTime(end - start);
        log.info("OperateLog:{}", OperateLog);
        operateLogMapper.insert(OperateLog);

        return result;

    }

    public int getOperateEmpId() {
        return CurrentHolder.getCurrentEmpId();
    }
}
