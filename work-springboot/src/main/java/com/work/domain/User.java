package com.work.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class User {
  @TableId(type = IdType.AUTO)
  private Integer userid;
  private String usertel;
  private String userpassword;
  private String usersex;
  private String userxueli;
  private String usermajor;
  private String userschool;
  private String userjianli;
  private String userjingli;
  private String usercard;
  private String userminzu;
  private String userqq;
  private String userwechat;
  private String userbrithday;
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserbrithday() {
    return userbrithday;
  }

  public void setUserbrithday(String userbrithday) {
    this.userbrithday = userbrithday;
  }

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }


  public String getUsertel() {
    return usertel;
  }

  public void setUsertel(String usertel) {
    this.usertel = usertel;
  }


  public String getUserpassword() {
    return userpassword;
  }

  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }


  public String getUsersex() {
    return usersex;
  }

  public void setUsersex(String usersex) {
    this.usersex = usersex;
  }


  public String getUserxueli() {
    return userxueli;
  }

  public void setUserxueli(String userxueli) {
    this.userxueli = userxueli;
  }


  public String getUsermajor() {
    return usermajor;
  }

  public void setUsermajor(String usermajor) {
    this.usermajor = usermajor;
  }


  public String getUserschool() {
    return userschool;
  }

  public void setUserschool(String userschool) {
    this.userschool = userschool;
  }


  public String getUserjianli() {
    return userjianli;
  }

  public void setUserjianli(String userjianli) {
    this.userjianli = userjianli;
  }


  public String getUserjingli() {
    return userjingli;
  }

  public void setUserjingli(String userjingli) {
    this.userjingli = userjingli;
  }


  public String getUsercard() {
    return usercard;
  }

  public void setUsercard(String usercard) {
    this.usercard = usercard;
  }


  public String getUserminzu() {
    return userminzu;
  }

  public void setUserminzu(String userminzu) {
    this.userminzu = userminzu;
  }


  public String getUserqq() {
    return userqq;
  }

  public void setUserqq(String userqq) {
    this.userqq = userqq;
  }


  public String getUserwechat() {
    return userwechat;
  }

  public void setUserwechat(String userwechat) {
    this.userwechat = userwechat;
  }

}
