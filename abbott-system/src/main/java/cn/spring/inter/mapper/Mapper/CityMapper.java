package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    List<City> findAll();
}