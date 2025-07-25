package com.itxuan.controller;

import com.itxuan.pojo.Clazz;
import com.itxuan.pojo.ClazzQueryParam;
import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.Result;
import com.itxuan.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam  param){
        log.info("班级的分页查询，参数：{}",param);
        PageResult<Clazz> pageResult = clazzService.page(param);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级，参数：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("获取班级信息，参数：{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级，参数：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping({"/{id}"})
    public Result delete(@PathVariable Integer id){
        log.info("删除班级，参数：{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("查询班级所有");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}

