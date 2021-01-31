package cn.spring.inter.utils;

import cn.spring.inter.bean.ResponseData;
import com.alibaba.fastjson.JSONObject;

public class ResponseUtility {
    /**
     *  处理时间格式 2019-11-28T06:52:09.724+0000 为 yyyy-MM-dd HH:mm:ss
     * */
    public static ResponseData handleData(
            String code, String status, String message,
            String detail, JSONObject data) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        responseData.setStatus(status);
        responseData.setMessage(message);
        responseData.setDetail(detail);
        responseData.setData(data);
        return responseData;
    }
}
