package cn.spring.inter.controller;

import cn.spring.inter.entity.AbbottExperience;
import cn.spring.inter.service.AbbottExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/abo/exp")
public class AbbottExperienceController {
    @Autowired
    private AbbottExperienceService abbottExperienceService;
    @RequestMapping("/findAll")
    public List<AbbottExperience> findAll(){
        return abbottExperienceService.findAll();
    }
}
