package com.itxuan.service;

import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.Student;
import com.itxuan.pojo.StudentQueryParam;
import com.itxuan.pojo.Violation;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam param);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void violation(Violation violation);
}
