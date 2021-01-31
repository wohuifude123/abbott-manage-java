package cn.spring.inter.service;

import cn.spring.inter.entity.AbbottExperience;
import java.util.List;

public interface AbbottExperienceService {
    List<AbbottExperience> findAll();
    Integer addOne(AbbottExperience abbottExperience);
    Integer updateOne(AbbottExperience abbottExperience);
    List<AbbottExperience> getAll(Integer start, Integer dataLength);
}
