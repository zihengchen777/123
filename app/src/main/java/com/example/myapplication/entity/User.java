package com.example.myapplication.entity;

public class User {
    private int id;
    private String uname;
    private String upwd;
    private int isDel;


    public void setId(int id) {
       this.id=id;
    }

    public void setUname(String uname) {
        this.uname=uname;
    }

    public void setUpwd(String upwd) {
        this.upwd=upwd;
    }

    public String getUpwd() {
        return upwd;
    }
}
