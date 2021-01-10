package cn.spring.inter.controller;

import cn.spring.inter.bean.AbbottInfo;
import cn.spring.inter.repository.AbbottInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@Controller
//@CrossOrigin(origins = "http://127.0.0.1:6600",maxAge = 3600)
@RestController
@RequestMapping("/abbott")
public class AbbottInfoController {
    @Autowired
    private AbbottInfoRepository abbottInfoRepository;

    /**
     * 查
     * @return
     */
    @PostMapping(value = "/list")
    public List<AbbottInfo> getUserList(){
        return abbottInfoRepository.findAll();
    }

    /**
     * 添加一个我的记录
     */
    @PostMapping(value = "/save")
    public AbbottInfo addAbbott(@RequestParam("username") String username, @RequestParam("age") Integer age){
        AbbottInfo abb=new AbbottInfo();
        abb.setUsername(username);
        abb.setAge(age);
        return abbottInfoRepository.save(abb);
    }

    @GetMapping(value = "/selectInfo/{id}")
    public AbbottInfo getAbbott(@PathVariable("id") Integer id){
        return abbottInfoRepository.findAbbottById(id);
    }

    /**
     * 修改用户信息
     * @param id
     * @param username
     * @param password
     * @return
     */
    @PutMapping(value = "updateInfo/{id}")
    public AbbottInfo updateAbbott(@PathVariable("id") Integer id,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password){
        AbbottInfo abb = new AbbottInfo();
        abb.setId(id);
        abb.setUsername(username);
        abb.setPassword(password);
        return abbottInfoRepository.save(abb);
    }

}
