package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.entity.Student;
import cn.spring.inter.service.StudentService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/v1/student", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData getAll(
            @RequestParam(value="start", required=false,defaultValue = "0") Integer start,
            @RequestParam(value="dataLength", required=false,defaultValue = "100") Integer dataLength,
            @RequestParam(value="sort", required=false, defaultValue = "DESC") String sort
    ){
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("tenantMessage");
        responseData.setDetail("添加tenantMessage");
        List<Student> studentList = studentService.getAll(start, dataLength, sort.toLowerCase());
        Integer studentCount = studentService.getCount();
        jsonResultObject.put("student", studentList);
        jsonResultObject.put("count", studentCount);
        responseData.setData(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/selectByPrimaryKey", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectByPrimaryKey(
            @RequestParam(value="id", required=false ) Integer id
    ) {

//        System.out.println("id == " + id);

        String responseCode = "500";
        String responseMessage = "fail";
        String responseDetail = "";

        String status = "select";

        JSONObject jsonResultObject = new JSONObject();
        ArrayList<Integer> arrStu = new ArrayList<>();
        if(id == null) {
            jsonResultObject.put("data", arrStu);
            responseDetail = "parameters are invalid";
        } else {
            Student stu = studentService.selectByPrimaryKey(id);
            if (stu != null) {
                responseCode = "200";
                responseMessage = "success";
                responseDetail = "select success";
            } else {
                responseDetail = "no students";
            }
            jsonResultObject.put("data", stu);
        }

        ResponseData responseData = handleResponseData(
                responseCode, responseMessage, status,
                responseDetail, jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/add", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData addOne(@RequestBody Map<String, String> student){
        Student stu =new Student();
        stu.setName(student.get("name"));
        stu.setAge(Integer.parseInt(student.get("age")));
        studentService.addOne(stu);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("0000");
        responseData.setStatus("success");
        responseData.setMessage("add");
        responseData.setDetail("添加个人经历");
        jsonResultObject.put("data", stu);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/insertSelective", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData insertSelective(@RequestBody Map<String, String> student){
        Student stu =new Student();
        stu.setName(student.get("name"));
        stu.setAge(Integer.parseInt(student.get("age")));
        stu.setAddress(student.get("address"));
        studentService.insertSelective(stu);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("add");
        responseData.setMessage("success");
        responseData.setDetail("添加学生信息");
        jsonResultObject.put("data", stu);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/name", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData selectByName(
            @RequestParam(value="name", required=false, defaultValue = "") String name,
            @RequestParam(value="sort", required=false, defaultValue = "desc") String sort,
            @RequestParam(value="start", required=false, defaultValue = "0") Integer start,
            @RequestParam(value="dataLength", required=false, defaultValue = "100") Integer dataLength){

//        System.out.println("name" + name);
//        System.out.println("sort" + sort);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("tenantMessage");
        responseData.setDetail("添加tenantMessage");
        List<Student> studentList = studentService.selectByName(name, sort.toLowerCase(), start, dataLength);
        Integer studentCount = studentService.getCount();
        jsonResultObject.put("student", studentList);
        jsonResultObject.put("count", studentCount);
        responseData.setData(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/updateByPrimaryKey", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData updateByPrimaryKey(@RequestBody Map<String, String> student){
        Student stu =new Student();
        stu.setId(Integer.parseInt(student.get("id")));
        stu.setName(student.get("name"));
        stu.setAge(Integer.parseInt(student.get("age")));
        studentService.updateByPrimaryKey(stu);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("update");
        responseData.setDetail("添加个人经历");
        jsonResultObject.put("data", stu);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/update", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData updateByPrimaryKeySelective(@RequestBody Map<String, String> student){
        Student stu =new Student();
        stu.setId(Integer.parseInt(student.get("id")));
        stu.setName(student.get("name"));
        stu.setAge(Integer.parseInt(student.get("age")));
        stu.setAddress(student.get("address"));
        int idUpdate = studentService.updateByPrimaryKeySelective(stu);

        String responseCode = "500";
        String responseMessage = "fail";
        if(idUpdate == 1) {
            responseCode = "200";
            responseMessage = "success";
        }
        String status = "update";
        String detail = "update student";
        JSONObject jsonResultObject = new JSONObject();
        jsonResultObject.put("data", idUpdate);
        ResponseData responseData = handleResponseData(
                responseCode, responseMessage, status,
                detail, jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/student/delete", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData deleteByPrimaryKey(@RequestBody Map<String, String> student) {
        int idDelected = studentService.deleteByPrimaryKey(Integer.parseInt(student.get("id")));
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("delete");
        responseData.setDetail("删除个人经历");
        jsonResultObject.put("data", idDelected);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    public ResponseData handleResponseData (
            String responseCode, String responseMessage, String status,
            String detail, JSONObject data) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(responseCode);
        responseData.setStatus(status);
        responseData.setMessage(responseMessage);
        responseData.setDetail(detail);
        responseData.setResult(data);
        responseData.setResult(data);
        return responseData;
    }

}
