package com.work.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Apply {
    @TableId(type = IdType.AUTO)
    private Integer applyid;
    private Integer userid;
    private String username;
    private Integer companyid;
    private String companyname;
    private Integer recruitid;
    private String recruitmajor;
    private String states;

    public Integer getApplyid() {
        return applyid;
    }

    public void setApplyid(Integer applyid) {
        this.applyid = applyid;
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

    public Integer getRecruitid() {
        return recruitid;
    }

    public void setRecruitid(Integer recruitid) {
        this.recruitid = recruitid;
    }

    public String getRecruitmajor() {
        return recruitmajor;
    }

    public void setRecruitmajor(String recruitmajor) {
        this.recruitmajor = recruitmajor;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}
