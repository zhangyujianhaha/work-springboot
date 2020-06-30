package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.Company;
import com.work.domain.Joins;
import com.work.domain.Recruit;
import com.work.domain.User;
import com.work.mapper.RecruitMapper;
import com.work.utlis.DataGridView;
import com.work.utlis.RetCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RecruitController {
    @Resource
    private RecruitMapper recruitMapper;
    @RequestMapping("company/recruit")
    public DataGridView companyRecruit(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                       HttpSession session){
        QueryWrapper<Recruit> wrapper=new QueryWrapper();
        Company company =(Company) session.getAttribute("user");
        wrapper.eq("companyid",company.getCompanyid());
        IPage<Recruit> pag=new Page<>(page,limit);
        recruitMapper.selectPage(pag,null);
        List<Recruit> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);

    }
    @RequestMapping("company/addrecruit")
    public RetCode adminAddUser(Recruit recruit,HttpSession session){
        Company company=(Company)session.getAttribute("user");
        QueryWrapper<Recruit> recruitQueryWrapper=new QueryWrapper<>();
        recruitQueryWrapper.eq("companyid",company.getCompanyid());
        List<Recruit> rlist=recruitMapper.selectList(recruitQueryWrapper);
        session.setAttribute("rlist",rlist);
        QueryWrapper<Recruit> wrapper=new QueryWrapper();

        int i=recruitMapper.insert(recruit);
        return new RetCode("1","添加成功");

    }
    @RequestMapping("company/updaterecruit")
    public RetCode adminUpdateUser(Recruit recruit){
        int i=recruitMapper.updateById(recruit);
        return new RetCode("1","修改成功");
    }
    @RequestMapping("company/deleterecruit")
    public RetCode adminDeleteUser(Recruit recruit){
        int i=recruitMapper.deleteById(recruit.getRecruitid());
        return new RetCode("1","删除成功");
    }
    @RequestMapping("user/recruit")
    public DataGridView userRecruit(Recruit recruit,@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        System.out.println(recruit.getRecrhangye());
        QueryWrapper<Recruit> wrapper=new QueryWrapper<>();
        wrapper.like(recruit.getRecrhangye()!=null&&!recruit.getRecrhangye().equals(""),"recrhangye",recruit.getRecrhangye())
                .like(recruit.getCompanyname()!=null&&!recruit.getCompanyname().equals(""),"companyname",recruit.getCompanyname())
                .like(recruit.getRecrmoney()!=null&&!recruit.getRecrmoney().equals(""),"recrmoney",recruit.getRecrmoney());
        IPage<Recruit> pag=new Page<>(page,limit);
        recruitMapper.selectPage(pag,wrapper);
        List<Recruit> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);

    }

}
