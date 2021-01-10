package cn.spring.inter.service;

import cn.spring.inter.entity.UserLogin;

import java.util.List;

public interface UserLoginService {
    List<UserLogin> findAll();
    List<UserLogin> findOne(String username);
}