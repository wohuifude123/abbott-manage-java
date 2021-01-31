package cn.spring.inter.service;

import cn.spring.inter.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll(Integer start, Integer dataLength, String sort);
    Integer getCount();
    Integer addOne(Student student);
    List<Student> selectByName(String name, String sort, Integer start, Integer dataLength);
    Integer updateByPrimaryKey(Student student);
    int deleteByPrimaryKey(Integer id);
    int insertSelective(Student student);
    int updateByPrimaryKeySelective(Student student);
    Student selectByPrimaryKey(Integer id);
}
