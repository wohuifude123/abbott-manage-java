package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper {
    List<User> findAll();
}
