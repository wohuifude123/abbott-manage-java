package cn.spring.inter.service.impl;

import cn.spring.inter.entity.User;
import cn.spring.inter.mapper.Mapper.UserMapper;
import cn.spring.inter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}