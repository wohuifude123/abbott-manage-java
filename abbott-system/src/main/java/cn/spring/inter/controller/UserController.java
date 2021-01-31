package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.entity.ConfigBean;
import cn.spring.inter.entity.ManageData;
import cn.spring.inter.entity.User;
import cn.spring.inter.entity.UserLogin;
import cn.spring.inter.service.ManageDataService;
import cn.spring.inter.service.UserLoginService;
import cn.spring.inter.service.UserService;

import com.abbott.common.controller.PasswordEncoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private ManageDataService manageDataService;
    /**
     * 经典的注解引入方式 就是在@Configuration注解下生存bean
     */
    @Autowired
    private ConfigBean configBean;

    @Autowired
    private Environment env;

    @RequestMapping("/user/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user/v1/login", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData getLoginUser(HttpServletRequest request, @RequestBody Map<String, String> person){
        List<UserLogin> list = new ArrayList<>();
        list = userLoginService.findOne(person.get("username"));

        String saltPassword = "password";
        if(env.getProperty("abbott.user.password.salt") != null) {
            saltPassword = env.getProperty("abbott.user.password.salt");
        }

        PasswordEncoder passwordEncoder = new PasswordEncoder(saltPassword, "MD5");

        String mapJson = JSON.toJSONString(list);
//        System.out.println("person == " + mapJson);
//        System.out.println("person list == " + mapJson);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = JSON.parseObject("{}");

        if(list.size() == 0) {
            responseData.setCode("0000");
            responseData.setStatus("fail");
            responseData.setMessage("login");
            responseData.setDetail("该账号未注册");
            responseData.setResult(jsonResultObject);
        } else {
            mapJson = JSON.toJSONString(list.get(0));
//            System.out.println(mapJson);
//            responseData.setCode("0001");
//            responseData.setStatus("success");
//            responseData.setMessage("login");
//            jsonResultObject.put("data", list.get(0));
//            responseData.setResult(jsonResultObject);
            JSONObject userJSON = new JSONObject();
            UserLogin userLogin = list.get(0);
            String passwordLogin = userLogin.getPassword(); // 数据库里存的密码
//            System.out.println("passwordLogin == " + passwordLogin);
            // 请求中的密码
//            System.out.println("password == " + passwordEncoder.encryption(person.get("password")));
            String passwordReq = passwordEncoder.encryption(person.get("password"));

            Boolean passwordIsTrue = passwordEncoder.isPasswordValid(passwordLogin, person.get("password"));
//            System.out.println("passwordIsTrue == " + passwordIsTrue);
            if(passwordLogin.equals(passwordReq)) {


                HttpSession session = request.getSession();

//                System.out.println(configBean.getName("王自强"));
                UserLogin userLoginReturn = new UserLogin(
                        list.get(0).getId(), list.get(0).getUsername(),
                        list.get(0).getAuthorization());

                String userLoginReturnStr = JSON.toJSONString(userLoginReturn);
                session.setAttribute("userInfo", userLoginReturnStr);
                jsonResultObject.put("data", userLoginReturn);

                if(list.get(0).getAuthorization() == 0) {
                    List<ManageData> listManageData = new ArrayList<>();
                    listManageData = manageDataService.selectAll(0, 100, "acs");
                    JSONObject manageDataObject = new JSONObject();
                    manageDataObject.put("urlList", listManageData);
                    jsonResultObject.put("manageData", manageDataObject);
                } else {
//                    jsonResultObject.put("manageData", null);
                    jsonResultObject.put("manageData", JSON.parseObject("{}"));
                }
                responseData = HandleResponseData(
                        "0001", "success", "login",
                        "用户登录成功", jsonResultObject);
            } else {
                responseData.setCode("0000");
                responseData.setStatus("fail");
                responseData.setMessage("login");
                responseData.setDetail("输入密码错误");
                responseData.setResult(jsonResultObject);
            }
        }
        return responseData;
    }

    @RequestMapping(value = "/v1/user/insert", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData insertUser(HttpServletRequest request, @RequestBody Map<String, String> userReq){
        ResponseData responseData = new ResponseData();
        User user = new User();
        JSONObject jsonResultObject = JSON.parseObject("{}");
        user.setUsername(userReq.get("username"));
        userService.insertSelective(user);
        int idInsert = user.getId();
        jsonResultObject.put("data", idInsert);
        responseData = HandleResponseData(
                "200", "success", "insert",
                "新建用户成功", jsonResultObject);
        return responseData;
    }

    public ResponseData HandleResponseData (
            String code, String status, String message,
            String detail, JSONObject jsonObject) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        responseData.setStatus(status);
        responseData.setMessage(message);
        responseData.setDetail(detail);
        responseData.setResult(jsonObject);
        responseData.setData(jsonObject);
        return responseData;
    }
}