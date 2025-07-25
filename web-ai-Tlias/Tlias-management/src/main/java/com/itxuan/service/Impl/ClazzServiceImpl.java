package com.itxuan.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itxuan.annotation.Log;
import com.itxuan.exception.ClazzHasStudentsException;
import com.itxuan.mapper.ClazzMapper;
import com.itxuan.mapper.EmpMapper;
import com.itxuan.mapper.StudentMapper;
import com.itxuan.pojo.Clazz;
import com.itxuan.pojo.ClazzQueryParam;
import com.itxuan.pojo.Emp;
import com.itxuan.pojo.PageResult;
import com.itxuan.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());

        List<Clazz> clazzlist = clazzMapper.list(param);
        Page<Clazz> p = (Page<Clazz>) clazzlist;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Log
    @Override
    public void save(Clazz clazz) {
    //添加班级
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Log
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Log
    @Override
    public void delete(Integer id) throws ClazzHasStudentsException {
        // 检查班级下是否有学生
        int studentCount = studentMapper.countByClazzId(id);
        if (studentCount > 0) {
            throw new ClazzHasStudentsException("无法删除班级，该班级下还有" + studentCount + "名学生");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
