package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String password;

    @Column(name = "create_time")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", createTime=" + createTime + "]";
    }


}