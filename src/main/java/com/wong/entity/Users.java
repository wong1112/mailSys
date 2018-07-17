package com.wong.entity;

import java.util.Date;

public class Users {
    private int uid;
    private String uname;
    private String upasswd;
    private String unickname;
    private Date udate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpasswd() {
        return upasswd;
    }

    public void setUpasswd(String upasswd) {
        this.upasswd = upasswd;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unikename) {
        this.unickname = unikename;
    }

    public java.sql.Date getUdate() {
        return new java.sql.Date(udate.getTime());
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }
}
