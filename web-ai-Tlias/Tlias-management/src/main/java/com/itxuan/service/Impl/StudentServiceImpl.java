package com.itxuan.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxuan.annotation.Log;
import com.itxuan.mapper.StudentMapper;
import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.Student;
import com.itxuan.pojo.StudentQueryParam;
import com.itxuan.pojo.Violation;
import com.itxuan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> page(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<Student> studentlist = studentMapper.list(param);
        Page<Student> p = (Page<Student>) studentlist;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Log
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        if (student.getViolationCount() == null) student.setViolationCount((short) 0);
        if (student.getViolationScore() == null) student.setViolationScore((short) 0);
        studentMapper.add(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Log
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Log
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Log
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void violation(Violation violation) {
        //1.获取到学生的违纪次数和违纪分
        Student student = studentMapper.getById(violation.getId());
        if (student != null) {
            //2.更新学生的违纪信息
            student.setViolationCount((short) (student.getViolationCount()+ 1));
            student.setViolationScore((short) (student.getViolationScore()+violation.getScore()));
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.update(student);
        }
    }


}
