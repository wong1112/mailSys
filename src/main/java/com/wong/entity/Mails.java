package com.wong.entity;

import java.util.Date;

public class Mails {
    private int mid;
    private String msender;
    private String maccepter;
    private String mtitle;
    private String mcontent;
    private Date mdate;
    private Boolean misread;



    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMsender() {
        return msender;
    }

    public void setMsender(String msender) {
        this.msender = msender;
    }

    public String getMaccepter() {
        return maccepter;
    }

    public void setMaccepter(String maccepter) {
        this.maccepter = maccepter;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }


    public Boolean getMisread() {
        return misread;
    }

    public void setMisread(Boolean misread) {
        this.misread = misread;
    }

    public java.sql.Date getMdate() {
        return new java.sql.Date(mdate.getTime());
    }

    public void setMdate(java.sql.Date date) {
        this.mdate = new Date(date.getTime());
    }

    public void setMdate() {
        this.mdate = new Date();
    }
}
