package cn.spring.inter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_board")
public class MessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;
    @Column(name = "name")
    private String name;//用户名
    @Column(name = "content")
    private String content;//密码
    @Column(name = "pubtime")
    private String pubtime;//密码
    @Column(name = "wechart")
    private String wechart;//密码

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
