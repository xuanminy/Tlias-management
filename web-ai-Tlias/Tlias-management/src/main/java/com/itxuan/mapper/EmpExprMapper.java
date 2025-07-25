package com.itxuan.mapper;

import com.itxuan.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    //批量保存工作经历信息
    void insertBatch(List<EmpExpr> exprsList);

    void deleteByEmpIds(List<Integer> empIds);
}
