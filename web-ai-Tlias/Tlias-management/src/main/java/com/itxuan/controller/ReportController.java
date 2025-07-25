package com.itxuan.controller;

import com.itxuan.pojo.ClazzOption;
import com.itxuan.pojo.GenderOption;
import com.itxuan.pojo.JobOption;
import com.itxuan.pojo.Result;
import com.itxuan.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderlist = reportService.getEmpGenderData();
        return Result.success(genderlist);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("统计班级人数");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计班级人数");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}
