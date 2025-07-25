package com.itxuan.service;

import com.itxuan.pojo.Emp;
import com.itxuan.pojo.EmpQueryParam;
import com.itxuan.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询的函数
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         LocalDate begin,LocalDate end);

    PageResult<Emp> page(EmpQueryParam param);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();
}
