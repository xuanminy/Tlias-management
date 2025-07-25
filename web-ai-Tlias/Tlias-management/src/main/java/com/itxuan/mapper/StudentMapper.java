package com.itxuan.mapper;

import com.itxuan.pojo.Student;
import com.itxuan.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    // 新增：根据班级ID查询学生数量
    @Select("select count(*) from student where clazz_id = #{clazzId}")
    int countByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParam param);

    void add(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    List<Map<String, Object>> countStudentCountData();

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();
}
