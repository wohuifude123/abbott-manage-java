package cn.spring.inter.entity;

import java.io.Serializable;

public class City implements Serializable {
    private Integer id;
    private String cityid;//用户名
    private String city;//密码
    private String provinceid;//密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }
}