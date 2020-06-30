package com.work.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Recruit {
  @TableId(type = IdType.AUTO)
  private Integer recruitid;
  private String recrhangye;
  private String recrmoney;
  private String recrxueli;
  private String recrmajor;
  private Integer companyid;
  private String companyname;


  public Integer getRecruitid() {
    return recruitid;
  }

  public void setRecruitid(Integer recruitid) {
    this.recruitid = recruitid;
  }


  public String getRecrhangye() {
    return recrhangye;
  }

  public void setRecrhangye(String recrhangye) {
    this.recrhangye = recrhangye;
  }


  public String getRecrmoney() {
    return recrmoney;
  }

  public void setRecrmoney(String recrmoney) {
    this.recrmoney = recrmoney;
  }


  public String getRecrxueli() {
    return recrxueli;
  }

  public void setRecrxueli(String recrxueli) {
    this.recrxueli = recrxueli;
  }


  public String getRecrmajor() {
    return recrmajor;
  }

  public void setRecrmajor(String recrmajor) {
    this.recrmajor = recrmajor;
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

}
