package cn.spring.inter.controller;

import cn.spring.inter.entity.City;
import cn.spring.inter.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/v1/findAll", method={ RequestMethod.POST, RequestMethod.GET })
    public List<City> findAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            String userLoginInfoStr = session.getAttribute("userInfo").toString();
//            System.out.println("userLoginInfoStr == " + userLoginInfoStr);
        }
        return cityService.findAll();
    }
}