package com.itxuan.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxuan.mapper.OperateLogMapper;
import com.itxuan.pojo.OperateLog;
import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.QueryString;
import com.itxuan.pojo.Student;
import com.itxuan.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(QueryString page) {
        PageHelper.startPage(page.getPage(),page.getPageSize());
        List<OperateLog> loglist = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) loglist;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
