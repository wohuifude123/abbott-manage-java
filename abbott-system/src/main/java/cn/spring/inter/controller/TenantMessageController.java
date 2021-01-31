package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.entity.TenantMessage;
import cn.spring.inter.service.TenantMessageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TenantMessageController {
    @Autowired
    private TenantMessageService tenantMessageService;

    @RequestMapping(value="/tenantMessage/v1", method={ RequestMethod.POST, RequestMethod.GET })
    public ResponseData getAll(
            @RequestParam(value="start", required=false,defaultValue = "0") Integer start,
            @RequestParam(value="dataLength", required=false,defaultValue = "100") Integer dataLength){
//        System.out.println("start="+start+"/dataLength="+dataLength);
        ResponseData responseData = new ResponseData();
        JSONObject jsonResultObject = new JSONObject();
        responseData.setCode("200");
        responseData.setStatus("success");
        responseData.setMessage("tenantMessage");
        responseData.setDetail("添加tenantMessage");
        List<TenantMessage> tenantMessageList = tenantMessageService.getAll(start, dataLength);
        Integer tenantMessageCount = tenantMessageService.getCount();
        jsonResultObject.put("tenant", tenantMessageList);
        jsonResultObject.put("count", tenantMessageCount);
        responseData.setData(jsonResultObject);
        return responseData;
    }
}
