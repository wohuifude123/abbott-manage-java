package cn.spring.inter.service;

import cn.spring.inter.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
}