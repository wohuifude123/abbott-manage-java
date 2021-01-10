package cn.spring.inter.entity;

public class ConfigBean {
    private String type = "Configuration注解生成bean实体";
    public String getName(String name) {
        return name + " ___ " + type;
    }
}