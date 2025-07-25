package com.itxuan.controller;

import com.itxuan.pojo.Dept;
import com.itxuan.pojo.Result;
import com.itxuan.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);//固定的代码，除了字节对象，在lombok中存在注解可以直接调用

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式
    @GetMapping
    public Result list(){
    List<Dept> depts =deptService.findAll();
    return Result.success(depts);
    }


    /*
    删除部门
    获取请求的参数，我们可以获取到HttpServiceRequest对象，然后获取请求的参数，但是得到的参数是字符串，是需要我们转化的
    而@RequestParam注解可以解决这个问题，但是如果请求里面没有携带参数(因为里面的required() default true，是true，改为false就不会报错了，但也没有参数)，就会直接报错
    前端传递的请求参数名与服务器端方法参数名一致，就可以直接省略@RequestParam注解了
     */
    @DeleteMapping
    public Result delete(Integer id){
    deptService.deleteById(id);
    log.info("根据ID删除部门：{}",id);
    return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        //json格式的参数，通常会使用一个实体对象进行接收。
        //规则：json数据的键名与方法形参对象的属性名相同，并需要@RequestBody进行接收。
        //则dept对象中的相同属性值被赋值。

        //因为在数据库中我的部门名称是唯一标识符，所以在添加之前我需要先判断我的部门名称是否已经存在
        //所以我有可能返回两个结果，一个是部门名称已经存在，一个是添加成功
        //这个我目前很难修改，我需要修改前端代码的逻辑
            log.info("添加部门：{}",dept);
            deptService.add(dept);
            return Result.success();
    }

    /*
    在修改部门信息的时候是分为两部的
    第一个部分是信息的回调：在修改信息之前我需要显示旧的信息，查询回显
    第二部分是修改信息
     */
    //携带的是路径参数,可以是多个参数,这是一个路径查询的方式，{id}是一个占位符,因为形参和路径中的参数名一致，所以可以省略@PathVariable("id")
    @GetMapping("/{id}")
    public Result fetInfo(@PathVariable Integer id){
        log.info("查询部门ID：{}",id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
