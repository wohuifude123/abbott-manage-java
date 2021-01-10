package cn.spring.inter.controller;

import cn.common.util.MD5;
import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.bean.UserChess;
import cn.spring.inter.repository.UserChessRepository;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chess")
public class UserChessController {
    @Autowired
    private UserChessRepository userChessRepository;
    /**
     * 查
     * @return
     */
    @PostMapping(value = "/list")
    public List<UserChess> getUserList(){
        return userChessRepository.findAll();
    }

    /**
     * 注册新的用户
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData addUserChess(HttpServletRequest request){
        UserChess uChess = new UserChess();
        ResponseData responseData = new ResponseData();
        JSONObject resData = new JSONObject();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String s= null;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
//            System.out.println("name:"+username+" password:"+password);

            List<UserChess> usersChess =  userChessRepository.findByUsername(username);

            int userSize = usersChess.size();

            if(userSize == 0) {
                uChess.setUsername(username);

                String passwordKey = null;
                try {
                    passwordKey = MD5.AddKey(password, "liu");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                uChess.setPassword(passwordKey);

                Date currentTime = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String currentTimeStr = simpleDateFormat.format(currentTime);

                long currentTimeLong = 0;

                try {
                    currentTimeLong = (simpleDateFormat.parse(currentTimeStr).getTime())/1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                uChess.setCreateTime(String.valueOf(currentTimeLong));
                uChess.setUserStatus(0);
                userChessRepository.save(uChess);
                responseData.setCode("0000");
                responseData.setStatus("success");
                responseData.setMessage("register");
            } else {
                responseData.setCode("0001");
                responseData.setStatus("fail");
                responseData.setMessage("register");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return userChessRepository.save(uChess);
        return responseData;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public ResponseData loginRequest(@RequestBody UserChess uChessReq){
        UserChess uChess = new UserChess();
        ResponseData responseData = new ResponseData();
        responseData.setCode("0001");
        responseData.setStatus("fail");
        responseData.setMessage("login");

        String username = uChessReq.getUsername();
        String password = uChessReq.getPassword();

        String passwordKey = null;

        try {
            passwordKey = MD5.AddKey(password, "liu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        uChess = userChessRepository.findByUsernameAndPassword(username, passwordKey);
//        int userId = uChess.getId();

        if(uChess != null) {
            System.out.println("userId == " + uChess.getId());
            responseData.setCode("0000");
            responseData.setStatus("success");
        }
        return responseData;
    }
}
