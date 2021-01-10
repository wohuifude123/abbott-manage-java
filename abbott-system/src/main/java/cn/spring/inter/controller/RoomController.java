package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/home")
@SessionAttributes("username")
public class RoomController {

    @RequestMapping("/list")
    public String cc(ModelMap model){
        return "index";
    }

    @RequestMapping(value = "/chess/room/create", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData createRoom(HttpServletRequest request, @RequestBody Map<String, String> person){

        HttpSession session = request.getSession();
        System.out.println(person.get("username"));
        session.setAttribute("data", "微信公众号：骄傲的程序员");
        ResponseData responseData = new ResponseData();
        responseData.setCode("0000");
        responseData.setStatus("success");
        responseData.setMessage("room");
        return responseData;
    }

    /**
     * 查
     * @return
     */
    @RequestMapping(value = "/other", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData getUserOther(HttpServletRequest request, @RequestBody Map<String, String> person){
        HttpSession session = request.getSession();
        System.out.println(person.get("username"));
        session.setAttribute("data", "微信公众号：骄傲的程序员");
        ResponseData responseData = new ResponseData();
        responseData.setCode("0000");
        responseData.setStatus("success");
        responseData.setMessage("room");
        return responseData;
    }
}
