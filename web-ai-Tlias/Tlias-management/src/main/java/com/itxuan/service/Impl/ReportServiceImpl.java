package com.itxuan.service.Impl;

import com.itxuan.mapper.EmpMapper;
import com.itxuan.mapper.StudentMapper;
import com.itxuan.pojo.ClazzOption;
import com.itxuan.pojo.GenderOption;
import com.itxuan.pojo.JobOption;
import com.itxuan.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        //调用mapper接口
        List<Map<String, Object>> list = empMapper.countEmpJobData();// pos = 职位 num=数量
        //2.组装结果
        List<Object> jobList = list.stream().map(datamap -> datamap.get("pos")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        //1.使用mapper接口，获取数据
        List<Map<String, Object>> list = empMapper.countEmpGenderData();
        return list;
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countStudentCountData();//num , className
        //2.组装结果
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();
        List<Object> clazzList = list.stream().map(datamap -> datamap.get("className")).toList();
        return new ClazzOption(clazzList, dataList);
    }

    @Override
    public List<java.util.Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.countStudentDegreeData();
        return list;
    }
}
