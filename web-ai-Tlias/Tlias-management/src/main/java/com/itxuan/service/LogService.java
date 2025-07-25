package com.itxuan.service;

import com.itxuan.pojo.OperateLog;
import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.QueryString;

public interface LogService {
    PageResult<OperateLog> page(QueryString page);
}
