package cn.spring.inter.controller;

import cn.spring.inter.bean.ResponseData;
import cn.spring.inter.entity.TechnologyArticle;
import cn.spring.inter.service.TechnologyArticleService;
import cn.spring.inter.utils.ExchangeConstants;
import cn.spring.inter.utils.ResponseUtility;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TechnologyArticleController {
    @Autowired
    private TechnologyArticleService technologyArticleService;

    @RequestMapping(value = "/v1/technologyArticle", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData getAll(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "dataLength", required = false, defaultValue = "100") Integer dataLength,
            @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort
    ) {
//        System.out.println("start="+start+"/dataLength="+dataLength);

        List<TechnologyArticle> technologyArticleList = technologyArticleService.selectAll(start, dataLength, sort);

//        String oldDateStr = "2021-01-26T22:52:00.000+0000";
//        String dateStrNew = ExchangeConstants.dealDateFormat(oldDateStr);
//        System.out.println("dateStrNew == " + dateStrNew);

        List<JSONObject> objTechnologyArticleList = new ArrayList<>();

        for(TechnologyArticle technologyArticle: technologyArticleList){
            JSONObject objTechnologyArticle = new JSONObject();
//            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(technologyArticle.getCreateDate()));
//            System.out.println(ExchangeConstants.dealDateFormat());
            objTechnologyArticle.put("id", technologyArticle.getId());
            objTechnologyArticle.put("title", technologyArticle.getTitle());
            objTechnologyArticle.put("content", technologyArticle.getContent());
            objTechnologyArticle.put("qrCodePath", technologyArticle.getQrCodePath());
            objTechnologyArticle.put("url", technologyArticle.getUrl());
            objTechnologyArticle.put("tags", technologyArticle.getTags());
            objTechnologyArticle.put("userID", technologyArticle.getUserID());
            objTechnologyArticle.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(technologyArticle.getCreateDate()));
            objTechnologyArticle.put("updateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(technologyArticle.getUpdateDate()));
            objTechnologyArticleList.add(objTechnologyArticle);
        }

        int count = technologyArticleService.getCount();
        JSONObject jsonResultObject = new JSONObject();
        jsonResultObject.put("data", objTechnologyArticleList);
        jsonResultObject.put("count", count);
        ResponseData responseData = ResponseUtility.handleData(
                "200", "success", "tenantMessage",
                "添加tenantMessage", jsonResultObject);
        return responseData;
    }

    @RequestMapping(value = "/v1/technologyArticle/selectById", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData selectById(
            @RequestBody Map<String, String> technology
    ) {
//        System.out.println("start="+start+"/dataLength="+dataLength);
        ResponseData responseData;
        JSONObject jsonTechnologyArticle;
        if (technology.get("id") == null) {
            responseData = ResponseUtility.handleData(
                    "404", "fail", "id is invalid",
                    "添加tenantMessage", JSON.parseObject("{}"));
        } else {
            int id = Integer.parseInt(technology.get("id"));
            TechnologyArticle technologyArticle = technologyArticleService.selectById(id);
            String strTechnologyArticle = JSONObject.toJSONString(technologyArticle);
            jsonTechnologyArticle = JSONObject.parseObject(strTechnologyArticle);
            responseData = ResponseUtility.handleData(
                    "200", "success", "tenantMessage",
                    "添加tenantMessage", jsonTechnologyArticle);
        }
        return responseData;
    }

    @RequestMapping(value = "/v1/technologyArticle/updateById", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData updateById(
            HttpServletRequest request,
            @RequestBody Map<String, String> technologyMap
    ) {
        ResponseData responseData;
        String code = "500";
        String message;
        String status = "fail";
        String detail = "updateById";

        HttpSession session = request.getSession();
        JSONObject jsonTechnologyArticle = JSON.parseObject("{}");
        if (session.getAttribute("userInfo") != null) {
            String strUserInfo = session.getAttribute("userInfo").toString();
//            System.out.println("strUserInfo == " + strUserInfo);
            JSONObject userInfoJSON = JSON.parseObject(strUserInfo);
            int authorization = Integer.parseInt(userInfoJSON.get("authorization").toString());
//            System.out.println("authorization == " + authorization);
            if(authorization == 0) {
                if (technologyMap.get("id") != null) {
                    TechnologyArticle technologyArticle = new TechnologyArticle();
                    if (technologyMap.get("title") != null) {
                        technologyArticle.setTitle(technologyMap.get("title"));
                    }
                    if (technologyMap.get("content") != null) {
                        technologyArticle.setContent(technologyMap.get("content"));
                    }

                    if (technologyMap.get("tags") != null) {
                        technologyArticle.setContent(technologyMap.get("tags"));
                    }

                    if (technologyMap.get("userID") != null) {
                        technologyArticle.setUserID(Integer.parseInt(technologyMap.get("userID")));
                    }

                    if(technologyMap.get("title") != null || technologyMap.get("content") != null ||
                            technologyMap.get("tags") != null) {
                        technologyArticle.setId(Integer.parseInt(technologyMap.get("id")));
//                System.out.println("Title == "+technologyArticle.getTitle());
                        int numUpdate = technologyArticleService.updateByPrimaryKeySelective(technologyArticle);

//                System.out.println("numUpdate == " + numUpdate);
                        if(numUpdate == 1) {
                            code = "200";
                            message = "update success";
                            status = "success";
                            detail = "技术文章成功更新";
                            jsonTechnologyArticle.put("data", numUpdate);
                        } else {
                            message = "update fail";
                            detail = "找不到更新的数据";
                            jsonTechnologyArticle.put("data", numUpdate);
                        }

                    } else {
                        message = "update fail";
                        detail = "没有更新字段";
                    }
                } else {
                    detail = "id无效";
                    message = "id is null";
                }

            } else {
                detail = "没有修改权限";
                message = "authorization is wrong";
            }
        } else {
            detail = "请先登录用户";
            message = "user is null";
        }

        responseData = ResponseUtility.handleData(
                code, status, message,
                detail, jsonTechnologyArticle);
        return responseData;
    }

    @RequestMapping(value = "/v1/technologyArticle/selectByKey", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData selectByKey(
            @RequestBody Map<String, String> technology
    ) {
        ResponseData responseData;
        String code = "500";
        String message = "";
        String status = "fail";
        String detail = "updateById";
        JSONObject jsonTechnologyArticle = JSON.parseObject("{}");

        int start = 0;
        int dataLength = 100;
        String sort = "desc";
        String searchKey = "";

        if(technology.get("searchKey") != null) {
            searchKey = technology.get("searchKey");
        }

        if(technology.get("start") != null) {
            start = Integer.parseInt(technology.get("start"));
        }

        if(technology.get("dataLength") != null) {
            start = Integer.parseInt(technology.get("dataLength"));
        }

        if(technology.get("sort") != null) {
            sort = technology.get("sort").toLowerCase();
        }

        List<TechnologyArticle> technologyArticleList = technologyArticleService.selectByKey(searchKey, start, dataLength, sort);
        jsonTechnologyArticle.put("data", technologyArticleList);

        int count = technologyArticleService.getCount();
        jsonTechnologyArticle.put("count", count);

//        System.out.println(technologyArticleList.size());

        if(technologyArticleList.size() == 0) {
            code = "200";
            message = "success";
            status = "select success";
            detail = "没有类似数据";
        } else {
            code = "200";
            message = "success";
            status = "select success";
            detail = "成功搜到数据";
        }

        responseData = ResponseUtility.handleData(
                code, status, message,
                detail, jsonTechnologyArticle);
        return responseData;
    }

}



