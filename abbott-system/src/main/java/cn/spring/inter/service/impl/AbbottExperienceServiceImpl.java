package cn.spring.inter.service.impl;

import cn.spring.inter.entity.AbbottExperience;
import cn.spring.inter.mapper.Mapper.AbbottExperienceMapper;
import cn.spring.inter.service.AbbottExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AbbottExperienceService")
public class AbbottExperienceServiceImpl implements AbbottExperienceService {

    @Autowired
    private AbbottExperienceMapper abbottExperienceMapper;

    @Override
    public List<AbbottExperience> findAll() {

        return abbottExperienceMapper.findAll();
    }

    @Override
    public Integer addOne(AbbottExperience abbottExperience) {

        return abbottExperienceMapper.addOne(abbottExperience);
    }

    @Override
    public Integer updateOne(AbbottExperience abbottExperience) {
        return abbottExperienceMapper.updateOne(abbottExperience);
    }

    @Override
    public List<AbbottExperience> getAll(Integer start, Integer dataLength) {
        return abbottExperienceMapper.getAll(start, dataLength);
    }
}