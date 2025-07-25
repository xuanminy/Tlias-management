package com.itxuan.service;

import com.itxuan.pojo.ClazzOption;
import com.itxuan.pojo.GenderOption;
import com.itxuan.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    ClazzOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
