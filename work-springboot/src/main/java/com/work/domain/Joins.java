package com.work.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Joins {
  @TableId(type = IdType.AUTO)
  private Integer joinid;
  private String joinwork;
  private String joinmoney;
  private String joinaddress;
  private Integer userid;
  private String username;
  private String miaoshu;

  public String getMiaoshu() {
    return miaoshu;
  }

  public void setMiaoshu(String miaoshu) {
    this.miaoshu = miaoshu;
  }

  public Integer getJoinid() {
    return joinid;
  }

  public void setJoinid(Integer joinid) {
    this.joinid = joinid;
  }


  public String getJoinwork() {
    return joinwork;
  }

  public void setJoinwork(String joinwork) {
    this.joinwork = joinwork;
  }


  public String getJoinmoney() {
    return joinmoney;
  }

  public void setJoinmoney(String joinmoney) {
    this.joinmoney = joinmoney;
  }


  public String getJoinaddress() {
    return joinaddress;
  }

  public void setJoinaddress(String joinaddress) {
    this.joinaddress = joinaddress;
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

}
