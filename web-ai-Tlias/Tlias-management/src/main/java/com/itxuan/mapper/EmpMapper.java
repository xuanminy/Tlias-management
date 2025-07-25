package com.itxuan.mapper;

import com.itxuan.pojo.Emp;
import com.itxuan.pojo.EmpQueryParam;
import com.itxuan.pojo.LoginInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


//    这里是使用的原始的分页查询所需要的语句
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
//    public Long count();
//
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc " +
//            "limit #{start},#{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);

//    基于pagehelper
//        @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
//        public List<Emp> list(String name, Integer gender,
//                              LocalDate begin, LocalDate end);

        public List<Emp> list(EmpQueryParam param);

    @Options(useGeneratedKeys = true, keyProperty = "id")//设置主键的回填，第一个参数表示是否使用数据库中的主键，第二个参数表示主键的字段名，这样emp参数中就拥有id字段了
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    //当你方法返回的类型是Map时，需要添加@MapKey注解，指定Map的key
    //但在这里是因为MybatisX插件的原因
    @MapKey("pos")//这里加了也没有用因为根本不是Map返回
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    @Select("select * from emp")
    List<Emp> findAll();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);

    @Select(" SELECT COUNT(*) FROM emp WHERE dept_id = #{id}")
    int getById(Integer id);
}
