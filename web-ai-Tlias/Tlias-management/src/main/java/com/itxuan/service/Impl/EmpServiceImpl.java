package com.itxuan.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxuan.annotation.Log;
import com.itxuan.mapper.EmpExprMapper;
import com.itxuan.mapper.EmpMapper;
import com.itxuan.pojo.*;
import com.itxuan.service.EmpLogService;
import com.itxuan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam param) {
//        设置分页参数
        PageHelper.startPage(param.getPage(),param.getPageSize());
        //执行查询
        List<Emp> emplist = empMapper.list(param);

        //解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
//    这个是原始的分页方法
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用Mapper接口获取总记录数
//        Long total = empMapper.count();
//        //2.调用Mapper接口获取结果列表
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //3.封装结果
//        return new PageResult<Emp>(total,rows);
//    }

    /**
     * @param //page 页码
     * @param //pageSize 页面包含数量大小
     * @return {@link PageResult }<{@link Emp }>
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,
//                                String name, Integer gender,
//                                LocalDate begin,LocalDate end) {
//        //设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //执行查询
//        List<Emp> emplist = empMapper.list(name,gender,begin,end);
//
//        //解析查询结果，并封装
//        Page<Emp> p = (Page<Emp>) emplist;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }

    @Log
    @Transactional(rollbackFor = {Exception.class})//经行事务控制，一般添加在进行多次的增删改查操作当中,默认是出现运行时异常RuntimeException才会回滚
    @Override
    public void save(Emp emp) {
        //保存员工的基本信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

//            int i = 10/0;//体现事务的传递行为的测试代码

            //保存员工工作经历信息
            List<EmpExpr> exprsList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprsList)){
                //为员工的工作经历设置员工id
                exprsList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprsList);
            }
        } finally {
            //在这里我是为了了解，事务传递行为的作用--目的：无论成功还是失败，都将日志输入到数据库
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工：" + emp.getName());
            empLogService.insertLog(empLog);
        }


    }

    @Log
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Log
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.删除员工的所有工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //3.添加员工新的工作经历信息
        List<EmpExpr> exprsList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprsList)){
            //为员工工作经历设置员工id
            exprsList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
                empExprMapper.insertBatch(exprsList);
            });
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.findAll();

    }

}
