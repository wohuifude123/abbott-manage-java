package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLoginMapper {
    List<UserLogin> findAll();
    List<UserLogin> findOne(String username);
}
