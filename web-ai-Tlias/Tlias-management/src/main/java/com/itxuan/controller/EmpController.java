package com.itxuan.controller;

import com.itxuan.pojo.Emp;
import com.itxuan.pojo.EmpQueryParam;
import com.itxuan.pojo.PageResult;
import com.itxuan.pojo.Result;
import com.itxuan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    分页查询
     */
    //参数过多不便于后期的维护，而且未来也有可能继续增加参数，所以使用对象封装参数
    @GetMapping
    public Result page(EmpQueryParam  param){
        log.info("分页查询，参数：{}",param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

    //添加员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("添加员工，参数：{}",emp);
        empService.save(emp);
        return Result.success();
    }


    /*
    批量删除员工--使用数组的方式接收数据
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("批量删除员工，参数：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工，参数：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("员工详情，参数：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("员工更新，参数：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        log.info("员工列表查询，查询所有员工");
        List<Emp> list = empService.list();
        return Result.success(list);
    }

}
