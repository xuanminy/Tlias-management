package com.itxuan.exception;

import com.itxuan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xuan
 * @description 全局异常处理器
 */
@Slf4j
@RestControllerAdvice//表明这是一个全局异常处理器
public class GlobalHandleException {

    @ExceptionHandler
    public Result handleException(Exception e) {
      log.error("服务器异常：{}",e);
      return Result.error("服务器异常,请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
      log.error("重复信息：{}",e);
      String row = e.getMessage();
      String row_msg = row.substring(row.indexOf("Duplicate entry"));
      String[] arr = row_msg.split(" ");
      String msg = arr[2]+"已存在";
      return Result.error(msg);
    }

    @ExceptionHandler(ClazzHasStudentsException.class)
    public Result handleClazzHasStudentsException(ClazzHasStudentsException e) {
        return Result.error(e.getMessage());
    }
    @ExceptionHandler(DeptHasEmpException.class)
    public Result handleDeptHasEmpException(DeptHasEmpException e) {
        return Result.error(e.getMessage());
    }

}
