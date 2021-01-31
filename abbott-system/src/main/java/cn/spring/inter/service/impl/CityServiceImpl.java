package cn.spring.inter.service.impl;

import cn.spring.inter.entity.City;
import cn.spring.inter.mapper.Mapper.CityMapper;
import cn.spring.inter.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CityService")
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findAll()  {
        return cityMapper.findAll();
    }
}