package com.example.jetpack.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class MyDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String titile;
    private String desc;
    private String icon;
    @Generated(hash = 2038652607)
    public MyDbBean(Long id, String titile, String desc, String icon) {
        this.id = id;
        this.titile = titile;
        this.desc = desc;
        this.icon = icon;
    }
    @Generated(hash = 720896287)
    public MyDbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitile() {
        return this.titile;
    }
    public void setTitile(String titile) {
        this.titile = titile;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
