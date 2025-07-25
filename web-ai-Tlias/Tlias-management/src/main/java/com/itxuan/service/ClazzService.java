package com.itxuan.service;

import com.itxuan.exception.ClazzHasStudentsException;
import com.itxuan.pojo.Clazz;
import com.itxuan.pojo.ClazzQueryParam;
import com.itxuan.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam param);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id) throws ClazzHasStudentsException;

    List<Clazz> findAll();
}
