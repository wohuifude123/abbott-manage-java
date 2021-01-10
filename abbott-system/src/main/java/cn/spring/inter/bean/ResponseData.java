package cn.spring.inter.bean;

import com.alibaba.fastjson.JSONObject;

public class ResponseData {
    private String code;
    private String status;
    private String message;
    private JSONObject result;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ResponseData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

}
