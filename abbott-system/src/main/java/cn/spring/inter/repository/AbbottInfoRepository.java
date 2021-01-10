package cn.spring.inter.repository;

import cn.spring.inter.bean.AbbottInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbbottInfoRepository extends JpaRepository<AbbottInfo, Integer>{
    //根据id查
    AbbottInfo findAbbottById(Integer id);
}