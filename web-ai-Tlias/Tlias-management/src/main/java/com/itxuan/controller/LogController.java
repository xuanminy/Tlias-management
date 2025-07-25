package com.itxuan.controller;

import com.itxuan.pojo.*;
import com.itxuan.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/log/page")
    public Result page(QueryString param) {
        PageResult<OperateLog> pageResult = logService.page(param);
        return Result.success(pageResult);
    }
}
