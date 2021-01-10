package cn.spring.inter.service.impl;

import cn.spring.inter.entity.UserLogin;
import cn.spring.inter.mapper.Mapper.UserLoginMapper;
import cn.spring.inter.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public List<UserLogin> findAll() {
        return userLoginMapper.findAll();
    }

    @Override
    public List<UserLogin> findOne(String username) {
        return userLoginMapper.findOne(username);

    }
}