package cn.spring.inter.service.impl;

import cn.spring.inter.entity.Student;
import cn.spring.inter.mapper.Mapper.StudentMapper;
import cn.spring.inter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll (Integer start, Integer dataLength, String sort) {
        return studentMapper.getAll(start, dataLength, sort);
    }

    @Override
    public Integer getCount() {
        return studentMapper.getCount();
    }

    @Override
    public Integer addOne(Student student) {
        return studentMapper.addOne(student);
    }

    @Override
    public List<Student> selectByName(String name, String sort, Integer start, Integer dataLength) {
        return studentMapper.selectByName(name, sort, start, dataLength);
    }
    @Override
    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKey(Student student){
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public int updateByPrimaryKeySelective(Student student){
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Student student) {
        return studentMapper.insertSelective(student);
    }
}
