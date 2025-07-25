package com.itxuan.mapper;

import com.itxuan.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //在mybatis中如果返回的字段名和实体类属性名一致，那么可以直接封装成对象
    //如果不一致就不会封装进去，解决方法通过Results注解来解决，还有别名来解决
//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })
//    @Select("select  id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")
    @Select("select  id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    @Select("select  id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
