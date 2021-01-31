package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getAll(Integer start, Integer dataLength, String sort);
    Integer getCount();
    Integer addOne(Student student);
    List<Student> selectByName(String name, String sort, Integer start, Integer dataLength);
    int deleteByPrimaryKey(Integer id);
    Integer updateByPrimaryKey(Student student);
    int updateByPrimaryKeySelective(Student student);
    int insertSelective(Student student);
    Student selectByPrimaryKey(Integer id);
}








