package com.itxuan.service.Impl;

import com.itxuan.annotation.Log;
import com.itxuan.exception.DeptHasEmpException;
import com.itxuan.mapper.DeptMapper;
import com.itxuan.mapper.EmpMapper;
import com.itxuan.pojo.Dept;
import com.itxuan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {

        return deptMapper.findAll();
    }

    @Log
    @Override
    public void deleteById(Integer id) throws DeptHasEmpException {
        //首先要查询里面有没有员工，这里可以抛自定义异常
            if(empMapper.getById(id) > 0){
            throw new DeptHasEmpException("无法删除部门，该部门下有员工");
        }
        deptMapper.deleteById(id);
    }

    @Log
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Log
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
