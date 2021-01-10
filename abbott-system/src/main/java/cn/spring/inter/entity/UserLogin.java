package cn.spring.inter.entity;

public class UserLogin {
    private int id;
    private String username;//用户名
    private String password;//密码
    private int authorization;
    private String device;

    public UserLogin() {
    }

    public UserLogin(int id, String username, int authorization) {
        this.id = id;
        this.username = username;
        this.authorization = authorization;
    }

    public UserLogin(int id, String username, String password, int authorization, String device) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int authorization) {
        this.authorization = authorization;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}