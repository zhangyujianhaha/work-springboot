package com.work.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Interview {
    @TableId(type = IdType.AUTO)
    private Integer interviewid;
    private String intertime;
    private  String interaddress;
    private Integer companyid;
    private String companyname;
    private Integer userid;
    private  String username;
    private Integer recruitid;
    private String recrmajor;

    public Integer getInterviewid() {
        return interviewid;
    }

    public void setInterviewid(Integer interviewid) {
        this.interviewid = interviewid;
    }

    public String getIntertime() {
        return intertime;
    }

    public void setIntertime(String intertime) {
        this.intertime = intertime;
    }

    public String getInteraddress() {
        return interaddress;
    }

    public void setInteraddress(String interaddress) {
        this.interaddress = interaddress;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRecruitid() {
        return recruitid;
    }

    public void setRecruitid(Integer recruitid) {
        this.recruitid = recruitid;
    }

    public String getRecrmajor() {
        return recrmajor;
    }

    public void setRecrmajor(String recrmajor) {
        this.recrmajor = recrmajor;
    }
}
