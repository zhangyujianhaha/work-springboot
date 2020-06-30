package com.work.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Company {
  @TableId(type = IdType.AUTO)
  private Integer companyid;
  private String companyname;
  private String comguimo;
  private String comleixing;
  private String comjianjie;
  private String comaddress;
  private String comphoto;
  private String comstarttime;
  private String companytel;
  private  String compassword;

  public String getCompassword() {
    return compassword;
  }

  public void setCompassword(String compassword) {
    this.compassword = compassword;
  }

  public String getCompanytel() {
    return companytel;
  }

  public void setCompanytel(String companytel) {
    this.companytel = companytel;
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


  public String getComguimo() {
    return comguimo;
  }

  public void setComguimo(String comguimo) {
    this.comguimo = comguimo;
  }


  public String getComleixing() {
    return comleixing;
  }

  public void setComleixing(String comleixing) {
    this.comleixing = comleixing;
  }


  public String getComjianjie() {
    return comjianjie;
  }

  public void setComjianjie(String comjianjie) {
    this.comjianjie = comjianjie;
  }


  public String getComaddress() {
    return comaddress;
  }

  public void setComaddress(String comaddress) {
    this.comaddress = comaddress;
  }


  public String getComphoto() {
    return comphoto;
  }

  public void setComphoto(String comphoto) {
    this.comphoto = comphoto;
  }


  public String getComstarttime() {
    return comstarttime;
  }

  public void setComstarttime(String comstarttime) {
    this.comstarttime = comstarttime;
  }

}
