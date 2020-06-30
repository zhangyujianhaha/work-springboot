package com.work.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.work.domain.Company;
import com.work.domain.Recruit;
import com.work.domain.User;
import com.work.mapper.CompanyMapper;
import com.work.mapper.RecruitMapper;
import com.work.mapper.UserMapper;
import com.work.utlis.LoginView;
import com.work.utlis.RetCode;
import com.work.utlis.SendUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Watchable;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private RecruitMapper recruitMapper;



    @RequestMapping("login/getCode")
    public void getCode(HttpServletResponse resp, HttpSession session) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        //得到code
        String code = captcha.getCode();
        //放到session
        session.setAttribute("code", code);
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }
    //登录验证
    @RequestMapping("login/login")
    @ResponseBody
    public RetCode loign(LoginView loginView, HttpSession session){
        String code1=(String)session.getAttribute("code");
        if(loginView.getCode().equals(code1)){
            if (loginView.getRadi().equals("1")){
                QueryWrapper<User> wrapper=new QueryWrapper();
                wrapper.eq("usertel",loginView.getName()).
                        eq("userpassword",loginView.getPassword());
                User user=userMapper.selectOne(wrapper);
                if (user!=null){
                    session.setAttribute("user",user);
                    return new RetCode("200","登陆成功");
                }
            }else if(loginView.getRadi().equals("2")){
                QueryWrapper<Company> wrapper=new QueryWrapper();
                wrapper.eq("companytel",loginView.getName()).
                        eq("compassword",loginView.getPassword());
                Company company=companyMapper.selectOne(wrapper);
                if (company!=null){
                    session.setAttribute("user",company);
                    QueryWrapper<Recruit> recruitQueryWrapper=new QueryWrapper<>();
                    recruitQueryWrapper.eq("companyid",company.getCompanyid());
                    List<Recruit> rlist=recruitMapper.selectList(recruitQueryWrapper);
                    session.setAttribute("rlist",rlist);
                    return new RetCode("300","登陆成功");
                }
            }else if(loginView.getRadi().equals("3")){
                if(loginView.getName().equals("123456")&&loginView.getPassword().equals("123456")){
                    session.setAttribute("user","admin");
                    return new RetCode("400","登陆成功");
                }
            }else {
                return new RetCode("600","用户名或密码错误");
            }
        }else {
            return new RetCode("500","验证码错误");
        }
        return null;

    }
    //验证码
    @RequestMapping("password/send")
    @ResponseBody
    public RetCode passwordSend(@RequestParam("usertel") String usertel, HttpSession session){
        SendUtil sendUtil=new SendUtil();
        System.out.println(usertel);
        int code=sendUtil.sendmassge(usertel);
        System.out.println(code);
        session.setAttribute("codes",code);
        return new RetCode("1","1");
    }
    @RequestMapping("login/reg")
    @ResponseBody
    public RetCode loginReg(LoginView loginView,HttpSession session){
        int code=(int)session.getAttribute("codes");

            if (String.valueOf(code).equals(loginView.getCode())){
                if(loginView.getRadi().equals("1")){
                    User user=new User();
                    QueryWrapper<User> wrapper=new QueryWrapper<>();
                    wrapper.eq("usertel",loginView.getName());
                    int j=userMapper.selectCount(wrapper);
                    if (j==0){
                        user.setUsertel(loginView.getName());
                        user.setUserpassword(loginView.getPassword());
                        int i=userMapper.insert(user);
                        return new RetCode("2","用户注册成功");
                    }else {
                        return new RetCode("3","该手机号已被注册");
                    }

                }else {
                    Company company=new Company();
                    QueryWrapper<Company> wrapper=new QueryWrapper<>();
                    wrapper.eq("companytel",loginView.getName());
                    int j=companyMapper.selectCount(wrapper);
                    if(j==0){
                        company.setCompanytel(loginView.getName());
                        company.setCompassword(loginView.getPassword());
                        int i=companyMapper.insert(company);
                        return new RetCode("4","公司注册成功");
                    }else {
                        return new RetCode("3","该手机号已被注册");
                    }
                }

            }else {
                return new RetCode("1","验证码错误,请重新输入");
            }

    }
    @RequestMapping("passwor/update")
    @ResponseBody
    public RetCode passwordUpdate(LoginView loginView, HttpSession session){
        int codes=(int) session.getAttribute("codes");

        if(String.valueOf(codes).equals(loginView.getCode())){
            if("1".equals(loginView.getRadi())){
                QueryWrapper<User> wrapper=new QueryWrapper<>();
                wrapper.eq("usertel",loginView.getName());
                User user=userMapper.selectOne(wrapper);
                if(user!=null){
                    user.setUserpassword(loginView.getPassword());
                    int i=userMapper.updateById(user);
                    return new RetCode("1","修改成功");
                }
                else {
                    return new RetCode("2","请输入正确的手机号");
                }

            }else {
                QueryWrapper<Company> wrapper=new QueryWrapper<>();
                wrapper.eq("companytel",loginView.getName());
                Company company=companyMapper.selectOne(wrapper);
                if (company!=null){
                    company.setCompassword(loginView.getPassword());
                    int i=companyMapper.updateById(company);
                    return new RetCode("3","修改成功");
                }else {
                    return new RetCode("2","请输入正确的手机号");
                }
            }
        }else {
            return new RetCode("5","短信验证码错误，请重新输入");
        }

    }
    @RequestMapping("login/out")
    public String loginOut(HttpSession session){
        session.setAttribute("user",null);
        return "/login";
    }




}


