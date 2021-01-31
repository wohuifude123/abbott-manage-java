package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.entity.AbbottExperience;
import cn.spring.inter.service.AbbottExperienceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/abo/exp")
public class AbbottExperienceController {
    @Autowired
    private AbbottExperienceService abbottExperienceService;
    @RequestMapping("/abo/exp/findAll")
    public List<AbbottExperience> findAll(){
        return abbottExperienceService.findAll();
    }

    @RequestMapping(value = "/abo/exp/v1/addOne", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData addOne(@RequestBody Map<String, String> experience){
        AbbottExperience abb=new AbbottExperience();
        abb.setCompany(experience.get("company"));
        abb.setInfo(experience.get("info"));
        abbottExperienceService.addOne(abb);
//        System.out.println(abb.getId());
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("0000");
        responseData.setStatus("success");
        responseData.setMessage("add");
        responseData.setDetail("添加个人经历");
        jsonResultObject.put("data", abb);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/abo/exp/v1/updateOne", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData updateOne(@RequestBody Map<String, String> experience){
        AbbottExperience abb=new AbbottExperience();
        abb.setId(Integer.parseInt(experience.get("id")));
        abb.setCompany(experience.get("company"));
        abb.setInfo(experience.get("info"));
        abbottExperienceService.updateOne(abb);
//        System.out.println(abb.getId());
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("0000");
        responseData.setStatus("success");
        responseData.setMessage("add");
        responseData.setDetail("添加个人经历");
        jsonResultObject.put("data", abb);
        responseData.setResult(jsonResultObject);
        return responseData;
    }

    @RequestMapping(value="/abo/exp/v1",method=RequestMethod.GET)
    public ResponseData getAll(
            @RequestParam(value="start", required=false,defaultValue = "0") Integer start,
            @RequestParam(value="dataLength", required=false,defaultValue = "100") Integer dataLength){
//        System.out.println("start="+start+"/dataLength="+dataLength);

        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("abbottExperiences");
        responseData.setDetail("查询Experiences");
        List<AbbottExperience> abbottExperienceList = abbottExperienceService.getAll(start, dataLength);
        jsonResultObject.put("experience", abbottExperienceList);
        responseData.setData(jsonResultObject);
        return responseData;
    }
}