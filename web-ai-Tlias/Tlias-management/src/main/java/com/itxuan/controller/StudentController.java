package com.itxuan.controller;

import com.itxuan.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.itxuan.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam param){
        log.info("分页查询");
        PageResult<Student> pageResult = studentService.page(param);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学生");
        studentService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("获取学生信息，参数：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息，参数：{}",student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除学生信息，参数：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("学生{}违纪，参数：{}",id, score);
        Violation violation = new Violation(id, score);
        studentService.violation(violation);
        return Result.success();
    }
}
