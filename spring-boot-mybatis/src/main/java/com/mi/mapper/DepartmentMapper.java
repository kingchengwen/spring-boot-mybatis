package com.mi.mapper;

import com.mi.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * Created by chengwen on 2019/7/3.
 */
//指定这是一个操作数据库的Mapper
//@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    //@Options注解用于获取自增主键返回给前端
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values (#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{department_name} where id=#{id}")
    public int updateDept(Department department);


}











