package com.abbott.inter.controller;

import com.abbott.inter.entity.Aggregation;
import com.abbott.inter.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
//@RequestMapping("/city")
public class AggregationController {

    @Autowired
    private AggregationService aggregationService;

    @RequestMapping(value = "/aggregation/v1", method={ RequestMethod.POST, RequestMethod.GET })
    public List<Aggregation> findAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            String userLoginInfoStr = session.getAttribute("userInfo").toString();
//            System.out.println("userLoginInfoStr == " + userLoginInfoStr);
        }
        return aggregationService.findAll(0, 100);
    }
}

